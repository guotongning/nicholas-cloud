package com.ning.springcloud.storage.map;

import java.io.Serializable;
import java.util.*;

/**
 * @author 不在能知，乃在能行 ——nicholas
 * @description
 * @date 2020/8/20 10:27
 */
public class NicoMap<K, V> implements Map<K, V>, Cloneable, Serializable {
    /**
     * 初始化容量 : 16
     */
    public static final int CAPACITY_INIT = 1 << 4;

    /**
     * 最大容量 : 1073741824
     * Integer.MAXVALUE = (1 << 31) - 1;
     */
    public static final int CAPACITY_MAXIMUM = 1 << 30;

    /**
     * 负载因子 : 0.75,当容量使用率达到75%，就会扩容 + rehash。
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //-----------静态工具方法------------
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= CAPACITY_MAXIMUM) ? CAPACITY_MAXIMUM : n + 1;
    }

    //---------------属性----------------
    NicoNode<K, V>[] table;

    Set<Entry<K, V>> entrySet;

    Set<K> keySet;

    Collection<V> values;

    int size;

    /**
     * 当 size = threshold * loadFactor 的时候，就该扩容了。
     */
    int threshold;

    final float loadFactor;

    public NicoMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.threshold = CAPACITY_INIT;
    }

    public NicoMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > CAPACITY_MAXIMUM)
            initialCapacity = CAPACITY_MAXIMUM;
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public NicoMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        NicoNode<K, V>[] tab = this.table;
        NicoNode<K, V> first, next;
        int hash;
        K k;
        //判断是不是first
        first = tab[(hash = hash(key)) & (tab.length - 1)];
        if ((k = first.key) != null && hash == first.hash && (key == k || key.equals(k))) {
            return true;
        } else {
            //判断后面的
            if ((next = first.next) != null) {
                do {
                    if (hash == next.hash && (key == next.key || key.equals(next.key))) {
                        return true;
                    }
                } while ((next = next.next) != null);
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        NicoNode<K, V> node;
        return (node = getNode(hash(key), key)) == null ? null : node.getValue();
    }

    @Override
    public boolean containsValue(Object value) {

        return false;
    }

    @Override
    public V put(K key, V value) {

        return null;
    }

    @Override
    public V remove(Object key) {

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    public void resize() {

    }

    @Override
    public void clear() {
        this.table = null;
        this.entrySet = null;
        this.keySet = null;
        this.values = null;
        this.size = 0;
        this.threshold = CAPACITY_INIT;
    }

    @Override
    public Set<K> keySet() {
        Set<K> ks = keySet;
        if (ks == null) {
            ks = new HashSet<>();
            for (NicoNode<K, V> first : table) {
                if (first != null) {
                    ks.add(first.key);
                    NicoNode<K, V> next = first.next;
                    if (next != null) {
                        do {
                            ks.add(next.key);
                        } while ((next = next.next) != null);
                    }
                }
            }
            keySet = ks;
        }
        return ks;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = this.values;
        if (values == null) {
            values = new ArrayList<>();
            for (NicoNode<K, V> first : table) {
                if (first != null) {
                    values.add(first.value);
                    NicoNode<K, V> next = first.next;
                    if (next != null) {
                        do {
                            values.add(next.value);
                        } while ((next = next.next) != null);
                    }
                }
            }
            this.values = values;
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = this.entrySet;
        if (entries == null) {
            entries = new HashSet<>();
            for (NicoNode<K, V> first : table) {
                if (first != null) {
                    entries.add(new NicoEntry<>(first.key, first.value));
                    NicoNode<K, V> next = first.next;
                    if (next != null) {
                        do {
                            entries.add(new NicoEntry<>(next.key, next.value));
                        } while ((next = next.next) != null);
                    }
                }
            }
            this.entrySet = entries;
        }
        return entries;
    }

    private NicoNode<K, V> getNode(int hash, Object key) {
        NicoNode<K, V>[] table;
        NicoNode<K, V> first, next;
        int length;
        K k = null;
        // 赋值 + 判断
        if ((table = this.table) != null && (length = table.length) > 0 && (first = table[(length - 1) & hash]) != null) {
            //检查是否可以返回目标位置链表第一个元素。
            if (first.hash == hash && (k = first.key) == key || (key != null && key.equals(k))) {
                return first;
            }
            if ((next = first.next) != null) {
                // 链表查询 On
                do {
                    if (next.hash == hash && (k = next.key) == key || (key != null && key.equals(k))) {
                        return next;
                    }
                } while ((next = next.next) != null);
            }
        }
        return null;
    }

    //------------hash table节点-------------
    static class NicoNode<K, V> implements Map.Entry<K, V> {
        private final int hash;
        private final K key;
        V value;
        NicoNode<K, V> next;

        NicoNode(int hash, K key, V value, NicoNode<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NicoNode<?, ?> nicoNode = (NicoNode<?, ?>) o;
            return Objects.equals(key, nicoNode.key) && Objects.equals(value, nicoNode.value);
        }
    }

    //------------Entry-------------
    static class NicoEntry<K, V> implements Entry<K, V> {
        final K k;
        V v;

        NicoEntry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public V setValue(V value) {
            V oldValue = v;
            v = value;
            return oldValue;
        }
    }
}
