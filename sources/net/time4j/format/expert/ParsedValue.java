package net.time4j.format.expert;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoException;

/* loaded from: classes4.dex */
class ParsedValue extends ParsedEntity<ParsedValue> {
    private Map<ChronoElement<?>, Object> map = null;
    private Object result = null;

    @Override // net.time4j.format.expert.ParsedEntity
    <E> E getResult() {
        return (E) this.result;
    }

    @Override // net.time4j.format.expert.ParsedEntity
    void setResult(Object obj) {
        this.result = obj;
    }

    ParsedValue() {
    }

    @Override // net.time4j.engine.ChronoEntity, net.time4j.engine.ChronoDisplay
    public boolean contains(ChronoElement<?> chronoElement) {
        Map<ChronoElement<?>, Object> map;
        if (chronoElement == null || (map = this.map) == null) {
            return false;
        }
        return map.containsKey(chronoElement);
    }

    @Override // net.time4j.engine.ChronoEntity, net.time4j.engine.ChronoDisplay
    public <V> V get(ChronoElement<V> chronoElement) {
        chronoElement.getClass();
        Map<ChronoElement<?>, Object> map = this.map;
        if (map != null && map.containsKey(chronoElement)) {
            return chronoElement.getType().cast(map.get(chronoElement));
        }
        throw new ChronoException("No value found for: " + chronoElement.name());
    }

    @Override // net.time4j.engine.ChronoEntity, net.time4j.engine.ChronoDisplay
    public int getInt(ChronoElement<Integer> chronoElement) {
        chronoElement.getClass();
        Map<ChronoElement<?>, Object> map = this.map;
        if (map == null || !map.containsKey(chronoElement)) {
            return Integer.MIN_VALUE;
        }
        return chronoElement.getType().cast(map.get(chronoElement)).intValue();
    }

    @Override // net.time4j.engine.ChronoEntity
    public Set<ChronoElement<?>> getRegisteredElements() {
        Map<ChronoElement<?>, Object> map = this.map;
        if (map == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(map.keySet());
    }

    @Override // net.time4j.format.expert.ParsedEntity
    void put(ChronoElement<?> chronoElement, int i) {
        chronoElement.getClass();
        Map map = this.map;
        if (map == null) {
            map = new HashMap();
            this.map = map;
        }
        map.put(chronoElement, Integer.valueOf(i));
    }

    @Override // net.time4j.format.expert.ParsedEntity
    void put(ChronoElement<?> chronoElement, Object obj) {
        chronoElement.getClass();
        if (obj != null) {
            Map map = this.map;
            if (map == null) {
                map = new HashMap();
                this.map = map;
            }
            map.put(chronoElement, obj);
            return;
        }
        Map<ChronoElement<?>, Object> map2 = this.map;
        if (map2 != null) {
            map2.remove(chronoElement);
            if (this.map.isEmpty()) {
                this.map = null;
            }
        }
    }
}
