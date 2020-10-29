/* 
 * The MIT License
 *
 * Copyright 2019 brunomnsilva@gmail.com.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.brunomnsilva.smartgraph.graph;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.Collection;
import java.util.Set;

/**
 * ADT Graph implementation that stores a collection of edges (and vertices) and
 * where each edge contains the references for the vertices it connects.
 * <br>
 * Does not allow duplicates of stored elements through <b>equals</b> criteria.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 * 
 * @author brunomnsilva
 */
public class GraphEdgeList<V, E> implements Graph<V, E> {

    /* inner classes are defined at the end of the class, so are the auxiliary methods 
     */
    private Set<Vertex<V>> vertices;
    private Set< Edge<E, V>> edges;

       public GraphEdgeList() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Collection<Vertex<V>> vertices() {
       Set<Vertex<V>> set = new HashSet<>(vertices);
        return set;
    }

    @Override
    public Collection<Edge<E, V>> edges() {
        Set<Edge<E,V>> set = new HashSet<>(edges);
        return set;
    }

    @Override
    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidVertexException {

        throw new NotImplementedException();
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        throw new NotImplementedException();

    }

    @Override
    public synchronized boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        //we allow loops, so we do not check if u == v
        throw new NotImplementedException();
    }

    @Override
    public synchronized Vertex<V> insertVertex(V vElement) throws InvalidVertexException {
        if (existsVertexWith(vElement)) {
            throw new InvalidVertexException("There's already a vertex with this element.");
        }

        MyVertex newVertex = new MyVertex(vElement);

        vertices.add(newVertex);

        return newVertex;
    }

    @Override
    public synchronized Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) 
            throws InvalidVertexException, InvalidEdgeException {

        throw new NotImplementedException();

    }


    @Override
    public synchronized V removeVertex(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        V element = v.element();

        //remove incident edges
        Iterable<Edge<E, V>> incidentEdges = incidentEdges(v);
        for (Edge<E, V> edge : incidentEdges) {
            edges.remove(edge);
        }

        vertices.remove(v);

        return element;
    }

    @Override
    public synchronized E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        throw new NotImplementedException();
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        throw new NotImplementedException();
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        throw new NotImplementedException();
    }

    private MyVertex vertexOf(V vElement) {
        for (Vertex<V> v : vertices) {
            if (v.element().equals(vElement)) {
                return (MyVertex) v;
            }
        }
        return null;
    }

    private boolean existsVertexWith(V vElement) {
        for(Vertex<V> v: vertices)
            if(v.element().equals(vElement))
                return true;
        return false;
    }

    private boolean existsEdgeWith(E edgeElement) {
        for(Edge<E,V> e: edges)
            if(e.element().equals(edgeElement))
                return true;
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("Graph with %d vertices and %d edges:\n", numVertices(), numEdges())
        );

        sb.append("--- Vertices: \n");
        for (Vertex<V> v : vertices) {
            sb.append("\t").append(v.toString()).append("\n");
        }
        sb.append("\n--- Edges: \n");
        for (Edge<E, V> e : edges) {
            sb.append("\t").append(e.toString()).append("\n");
        }
        return sb.toString();
    }

    class MyVertex implements Vertex<V> {

        V element;

        public MyVertex(V element) {
            this.element = element;
        }

        @Override
        public V element() {
            return this.element;
        }

        @Override
        public String toString() {
            return "Vertex{" + element + '}';
        }
    }

    class MyEdge implements Edge<E, V> {

        E element;
        Vertex<V> vertexOutbound;
        Vertex<V> vertexInbound;

        public MyEdge(E element, Vertex<V> vertexOutbound, Vertex<V> vertexInbound) {
            this.element = element;
            this.vertexOutbound = vertexOutbound;
            this.vertexInbound = vertexInbound;
        }

        @Override
        public E element() {
            return this.element;
        }

        public boolean contains(Vertex<V> v) {
            return (vertexOutbound == v || vertexInbound == v);
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices = new Vertex[2];
            vertices[0] = vertexOutbound;
            vertices[1] = vertexInbound;

            return vertices;
        }

        @Override
        public String toString() {
            return "Edge{{" + element + "}, vertexOutbound=" + vertexOutbound.toString()
                    + ", vertexInbound=" + vertexInbound.toString() + '}';
        }
    }

    /**
     * Checks whether a given vertex is valid and belongs to this graph
     *
     * @param v
     * @return
     * @throws InvalidVertexException
     */
    private MyVertex checkVertex(Vertex<V> v) throws InvalidVertexException {
        if(v == null) throw new InvalidVertexException("Null vertex.");
        
        MyVertex vertex;
        try {
            vertex = (MyVertex) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("Not a vertex.");
        }

        if (!vertices.contains(vertex)) {
            throw new InvalidVertexException("Vertex does not belong to this graph.");
        }

        return vertex;
    }

    private MyEdge checkEdge(Edge<E, V> e) throws InvalidEdgeException {
        if(e == null) throw new InvalidEdgeException("Null edge.");
        
        MyEdge edge;
        try {
            edge = (MyEdge) e;
        } catch (ClassCastException ex) {
            throw new InvalidVertexException("Not an adge.");
        }

        if (!edges.contains(edge)) {
            throw new InvalidEdgeException("Edge does not belong to this graph.");
        }

        return edge;
    }
}
