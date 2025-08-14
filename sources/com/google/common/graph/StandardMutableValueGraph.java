package com.google.common.graph;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
final class StandardMutableValueGraph<N, V> extends StandardValueGraph<N, V> implements MutableValueGraph<N, V> {
    private final ElementOrder<N> incidentEdgeOrder;

    @Override // com.google.common.graph.AbstractValueGraph, com.google.common.graph.AbstractBaseGraph, com.google.common.graph.BaseGraph
    public ElementOrder<N> incidentEdgeOrder() {
        return this.incidentEdgeOrder;
    }

    StandardMutableValueGraph(AbstractGraphBuilder<? super N> abstractGraphBuilder) {
        super(abstractGraphBuilder);
        this.incidentEdgeOrder = (ElementOrder<N>) abstractGraphBuilder.incidentEdgeOrder.cast();
    }

    @Override // com.google.common.graph.MutableValueGraph
    public boolean addNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        if (containsNode(n2)) {
            return false;
        }
        addNodeInternal(n2);
        return true;
    }

    private GraphConnections<N, V> addNodeInternal(N n2) {
        GraphConnections<N, V> graphConnectionsNewConnections = newConnections();
        Preconditions.checkState(this.nodeConnections.put(n2, graphConnectionsNewConnections) == null);
        return graphConnectionsNewConnections;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V putEdgeValue(N n2, N n3, V v) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        Preconditions.checkNotNull(v, "value");
        if (!allowsSelfLoops()) {
            Preconditions.checkArgument(!n2.equals(n3), "Cannot add self-loop edge on node %s, as self-loops are not allowed. To construct a graph that allows self-loops, call allowsSelfLoops(true) on the Builder.", n2);
        }
        GraphConnections<N, V> graphConnectionsAddNodeInternal = this.nodeConnections.get(n2);
        if (graphConnectionsAddNodeInternal == null) {
            graphConnectionsAddNodeInternal = addNodeInternal(n2);
        }
        V vAddSuccessor = graphConnectionsAddNodeInternal.addSuccessor(n3, v);
        GraphConnections<N, V> graphConnectionsAddNodeInternal2 = this.nodeConnections.get(n3);
        if (graphConnectionsAddNodeInternal2 == null) {
            graphConnectionsAddNodeInternal2 = addNodeInternal(n3);
        }
        graphConnectionsAddNodeInternal2.addPredecessor(n2, v);
        if (vAddSuccessor == null) {
            long j = this.edgeCount + 1;
            this.edgeCount = j;
            Graphs.checkPositive(j);
        }
        return vAddSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V putEdgeValue(EndpointPair<N> endpointPair, V v) {
        validateEndpoints(endpointPair);
        return putEdgeValue(endpointPair.nodeU(), endpointPair.nodeV(), v);
    }

    @Override // com.google.common.graph.MutableValueGraph
    public boolean removeNode(N n2) {
        Preconditions.checkNotNull(n2, "node");
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n2);
        if (graphConnections == null) {
            return false;
        }
        if (allowsSelfLoops() && graphConnections.removeSuccessor(n2) != null) {
            graphConnections.removePredecessor(n2);
            this.edgeCount--;
        }
        Iterator<N> it = graphConnections.successors().iterator();
        while (it.hasNext()) {
            ((GraphConnections) Objects.requireNonNull(this.nodeConnections.getWithoutCaching(it.next()))).removePredecessor(n2);
            this.edgeCount--;
        }
        if (isDirected()) {
            Iterator<N> it2 = graphConnections.predecessors().iterator();
            while (it2.hasNext()) {
                Preconditions.checkState(((GraphConnections) Objects.requireNonNull(this.nodeConnections.getWithoutCaching(it2.next()))).removeSuccessor(n2) != null);
                this.edgeCount--;
            }
        }
        this.nodeConnections.remove(n2);
        Graphs.checkNonNegative(this.edgeCount);
        return true;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V removeEdge(N n2, N n3) {
        Preconditions.checkNotNull(n2, "nodeU");
        Preconditions.checkNotNull(n3, "nodeV");
        GraphConnections<N, V> graphConnections = this.nodeConnections.get(n2);
        GraphConnections<N, V> graphConnections2 = this.nodeConnections.get(n3);
        if (graphConnections == null || graphConnections2 == null) {
            return null;
        }
        V vRemoveSuccessor = graphConnections.removeSuccessor(n3);
        if (vRemoveSuccessor != null) {
            graphConnections2.removePredecessor(n2);
            long j = this.edgeCount - 1;
            this.edgeCount = j;
            Graphs.checkNonNegative(j);
        }
        return vRemoveSuccessor;
    }

    @Override // com.google.common.graph.MutableValueGraph
    @CheckForNull
    public V removeEdge(EndpointPair<N> endpointPair) {
        validateEndpoints(endpointPair);
        return removeEdge(endpointPair.nodeU(), endpointPair.nodeV());
    }

    private GraphConnections<N, V> newConnections() {
        if (isDirected()) {
            return DirectedGraphConnections.of(this.incidentEdgeOrder);
        }
        return UndirectedGraphConnections.of(this.incidentEdgeOrder);
    }
}
