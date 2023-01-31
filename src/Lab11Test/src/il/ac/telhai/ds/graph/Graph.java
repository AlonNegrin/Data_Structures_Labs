package il.ac.telhai.ds.graph;

import java.util.*;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E> {

    private TreeMap<V, LinkedList<Edge<E>>> vertices;

    public Graph() {
        vertices = new TreeMap<>();
    }

    @Override
    public void add(V v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, new LinkedList<Edge<E>>());
        }
    }

    @Override
    public E getEdge(V u, V v) {
        LinkedList<Edge<E>> ll = vertices.get(u);
        for (Edge<E> edge : ll) {
            if (edge.destination == v) {
                return edge.weight;
            }
        }
        return null;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        add(u);
        add(v);

        //check for u
        LinkedList<Edge<E>> ll = vertices.get(u);
        for (Edge<E> edge : ll) {
            if (edge.destination == v) {
                edge.weight = edgeLabel;
            }
        }
        if (!areAdjacent(u, v)) {
            ll.add(new Edge<E>(u, v, edgeLabel));
        }

        //check for v
        ll = vertices.get(v);
        for (Edge<E> edge : ll) {
            if (edge.destination == u) {
                edge.weight = edgeLabel;
            }
        }
        if (!areAdjacent(v, u)) {
            ll.add(new Edge<E>(u, v, edgeLabel));
        }
        return edgeLabel;
    }

    @Override
    public boolean containsVertex(V v) {
        return vertices.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        if (!containsVertex(v)) return;
        LinkedList<Edge<E>> ll = vertices.get(v);
        for (Edge<E> edge : ll) {
            removeEdge(v, edge.destination);
        }
        vertices.remove(v);
    }

    @Override
    public E removeEdge(V u, V v) {
        LinkedList<Edge<E>> ll = vertices.get(u);
        for (Edge<E> edge : ll) {
            if (edge.destination == v) {
                E weight = edge.weight;
                ll.remove(edge);
                ll = vertices.get(v);
                for (Edge<E> edge2 : ll) {
                    if (edge2.destination == v) {
                        ll.remove(edge2);
                    }
                }
                return weight;
            }
        }
        return null;
    }

    @Override
    public double getWeight(V u, V v) throws RuntimeException {
        E edge = getEdge(u, v) != null ? getEdge(u, v) : getEdge(v, u);
        if (edge == null) return 0;
        if (edge instanceof Weighted)
            return ((Weighted) edge).getWeight();
        if (edge instanceof java.lang.Number)
            return (double) edge;
        throw new IllegalArgumentException();
    }

    public TreeMap<V, Double> distancesFrom(V vertex) {
        PriorityQueue<Vertex<V>> queue = new PriorityQueue<>();
        TreeMap<V, Double> distances = new TreeMap<>();
        // init
        for (V v : vertices.keySet()) {
            if (v == vertex) {
                Vertex<V> ver = new Vertex<V>(vertex);
                ver.distance = 0.0;
                queue.add(ver);
                continue;
            }
            queue.add(new Vertex<V>(v));
        }
        // loop
        while (!queue.isEmpty()) {
            Vertex<V> _vertex = queue.poll();
            Iterator<Vertex<V>> it = queue.iterator();
            while (it.hasNext()) {
                Vertex<V> _next = it.next();
                if (areAdjacent(_vertex.key, _next.key)) {
                    double dist = getWeight(_vertex.key, _next.key);
                    if (dist + _vertex.distance < _next.distance) {
                        queue.remove(_next);
                        queue.add(new Vertex<>(_next.key, _vertex.distance + dist));
                        it = queue.iterator();
                    }
                }
            }
            if (_vertex.distance != Double.MAX_VALUE)
                distances.put(_vertex.key, _vertex.distance);
        }
        return distances;
    }

    public String toString() {

        StringBuilder s = new StringBuilder("");
        if (!vertices.isEmpty()) {
            Set<V> keys = vertices.keySet();
            int cnt = 0;
            for (V key : keys) {
                s.append(key);
                s.append(",");
            }
            s.setLength(s.length() - 1);
        }
        return s.toString();

    }

    @Override
    public String toStringExtended() {
        StringBuilder sb = new StringBuilder();
        for (V v : vertices.keySet()) {
            sb.append(v);
            sb.append(":");
            if (!vertices.get(v).isEmpty()) {
                for (Edge<E> edge : vertices.get(v)) {
                    sb.append("{");
                    sb.append(edge.source);
                    sb.append(",");
                    sb.append(edge.destination);
                    sb.append("}");
                    if (edge.weight instanceof Weighted || edge.weight instanceof Number) {
                        sb.append("(");
                        sb.append(edge.weight);
                        sb.append(")");
                    }
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append('\n');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        if (!vertices.containsKey(u) || !vertices.containsKey(v)) return false;
        return vertices.get(u).stream().anyMatch(edge -> edge.containsVerti(v));

    }

    private class Edge<U> {
        private V source;
        private V destination;
        private U weight;

        public Edge(V source, V destination, U weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public boolean containsVerti(V v) {
            return source.equals(v) || destination.equals(v);
        }
    }

    private class Vertex<V> implements Comparable<Vertex<V>> {
        private V key;
        private Double distance;

        public Vertex(V key) {
            this.key = key;
            this.distance = Double.MAX_VALUE;
        }

        public Vertex(V key, Double distance) {
            this.key = key;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex<V> o) {
            if (distance < o.distance) return 1;
            if (distance > o.distance) return -1;
            return 0;
        }
    }
}
