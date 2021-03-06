package engine;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.*;

public class Signal<T> extends EventStream implements Supplier<T> {

    private T value;

    public Signal(T value) {
        this.value = value;
    }

    public void edit(UnaryOperator<T> o) {
        set(o.apply(get()));
    }

    @Override
    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
        sendEvent();
    }

    public void set(T... values) {
        Arrays.asList(values).forEach(this::set);
    }

    //Interesting functions
    public Signal<List<T>> buffer(EventStream e) {
        return e.with(collect(new LinkedList(), List::add), s -> {
            s.sendEvent();
            s.set(new LinkedList());
        });
    }

    public Signal<List<T>> bufferThrottle(double interval) {
        return buffer(throttle(interval));
    }

    public <R> Signal<R> collect(R r, BiConsumer<R, T> o) {
        return with(new Signal<>(r), s -> o.accept(s.get(), get()));
    }

    public Signal<T> combine(Signal<T>... other) {
        Signal<T> newSig = copy();
        for (Signal<T> o : other) {
            o.with(newSig, s -> s.set(o.get()));
        }
        return newSig;
    }

    public Signal<T> copy() {
        return toSignal(this);
    }

    public <R, S> Signal<S> combineLatest(Signal<R> other, BiFunction<T, R, S> b) {
        return combineEventStreams(other).map(() -> b.apply(get(), other.get()));
    }

    public Signal<T> debounce(double interval) {
        return throttle(interval).toSignal(this);
    }

    public Signal<T> distinct() {
        return with(new Signal(get()), s -> {
            if (!Objects.equals(get(), s.get())) {
                s.set(get());
            }
        });
    }

    public Signal<T> doForEach(Consumer<T> c) {
        c.accept(get());
        return with(new Signal(get()), s -> {
            c.accept(get());
            s.set(get());
        });
    }

    public <R> Signal<R> flatMap(Function<T, Signal<R>> f) {
        Signal<R> ret = f.apply(get());
        map(f).forEach(s -> s.forEach(ret::set).addChild(ret)).addChild(ret);
        return ret;
    }

    public Signal<T> find(Predicate<T> p) {
        return filter(p).first(1);
    }

    public Signal<T> filter(Signal<Boolean> s) {
        return s.addChild(filter(t -> s.get()));
    }

    public Signal<T> filter(Predicate<T> p) {
        return with(new Signal(p.test(get()) ? get() : null), s -> {
            if (p.test(get())) {
                s.set(get());
            }
        });
    }

    public Signal<T> first(int n) {
        return until(count().map(i -> i <= n));
    }

    public Signal<T> forEach(Consumer<T> c) {
        return with(new Signal(get()), s -> {
            c.accept(get());
            s.set(get());
        });
    }

    public <R> Signal<R> map(Function<T, R> f) {
        return map(() -> f.apply(get()));
    }

    public <R> Signal<R> ofType(Class<R> c) {
        return filter(c::isInstance).map(c::cast);
    }

    public Signal<T> printEach() {
        return forEach(System.out::println);
    }

    public Signal<T> printStackTraceEach() {
        return forEach(t -> new RuntimeException().printStackTrace());
    }

    public <R> Signal<R> reduce(R r, BiFunction<T, R, R> f) {
        return with(new Signal<>(r), s -> s.edit(v -> f.apply(get(), v)));
    }

    public Signal<T> until(Signal<Boolean> s) {
        return until(t -> s.get());
    }

    public Signal<T> until(Predicate<T> p) {
        return with(new Signal(p.test(get()) ? get() : null), s -> {
            if (p.test(get())) {
                s.set(get());
            } else {
                s.destroy();
            }
        });
    }
}
