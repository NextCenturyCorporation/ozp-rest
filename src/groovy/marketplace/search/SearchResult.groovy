package marketplace.search

import marketplace.Paging

/**
 * This class decorates a list of search results with total, max, offset, etc
 */
class SearchResult<E> implements List<E>, Paging<E> {
    List<E> items
    int total
    SearchCriteria searchCriteria
    Map aggs

    public Integer getOffset() {
        searchCriteria.offset
    }

    public Integer getMax() {
        searchCriteria.max
    }

    public Map<String, List<String>> getFilters() {
        searchCriteria.filters
    }

    public String getQueryString() {
        searchCriteria.queryString
    }

    public String getOrder() {
        searchCriteria.order
    }

    public String getSort() {
        searchCriteria.sort
    }

    Object[] toArray() {
        items.toArray()
    }

    Object[] toArray(Object[] objects) {
        items.toArray(objects)
    }

    E get(int index) {
        items.get(index)
    }

    List<E> subList(int from, int to) {
        items.subList(from, to)
    }

    int size() {
        return items.size()
    }

    boolean retainAll(Collection<?> c) {
        return items.retainAll(c)
    }

    boolean removeAll(Collection<?> c) {
        items.removeAll(c)
    }

    boolean remove(Object o) {
        items.remove(o)
    }

    E remove(int index) {
        items.remove(index)
    }

    ListIterator<E> listIterator(int index) {
        items.listIterator(index)
    }

    ListIterator<E> listIterator() {
        items.listIterator()
    }

    int lastIndexOf(Object o) {
        items.lastIndexOf(o)
    }

    boolean isEmpty() {
        items.isEmpty()
    }

    int indexOf(Object o) {
        items.indexOf(o)
    }

    void clear() {
        items.clear()
    }

    boolean add(E e) {
        items.add(e)
    }

    void add(int index, E e) {
        items.add(index, e)
    }

    boolean addAll(Collection<? extends E> c) {
        items.addAll(c)
    }

    boolean addAll(int index, Collection<? extends E> c) {
        items.addAll(index, c)
    }

    Iterator<E> iterator() {
        items.iterator()
    }

    E set(int index, E element) {
        items.set(index, element)
    }

    boolean contains(Object o) {
        items.contains(o)
    }

    boolean containsAll(Collection<?> c) {
        items.containsAll(c)
    }

    int hashCode() {
        items.hashCode()
    }

    boolean equals(Object other) {
        other instanceof SearchResult && items.equals(other.items)
    }
}
