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

import java.util.Collection;

/**
 * A graph is made up of a set of vertices connected by edges, where the edges 
 * have no direction associated with them, i.e., they establish a two-way connection.
 *
 * @param <V> Type of element stored at a vertex
 * @param <E> Type of element stored at an edge
 * 
 * @see Edge
 * @see Vertex
 */
public interface Graph<V, E> {

    public int numVertices();

    public int numEdges();

    public Collection<Vertex<V>> vertices();

    public Collection<Edge<E, V>> edges();

    public Collection<Edge<E, V>> incidentEdges(Vertex<V> v)
            throws InvalidVertexException;

    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e)
            throws InvalidVertexException, InvalidEdgeException;

    public boolean areAdjacent(Vertex<V> u, Vertex<V> v)
            throws InvalidVertexException;

    public Vertex<V> insertVertex(V vElement)
            throws InvalidVertexException;

    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement)
            throws InvalidVertexException, InvalidEdgeException;



    public  V removeVertex(Vertex<V> v) throws InvalidVertexException;

    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException;
    

    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException;
    

    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException;
}
