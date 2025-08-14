package com.google.common.graph;

import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public interface ValueGraph<N, V> extends BaseGraph<N> {
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    Set<N> adjacentNodes(N n2);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    boolean allowsSelfLoops();

    Graph<N> asGraph();

    @Override // com.google.common.graph.BaseGraph
    int degree(N n2);

    @CheckForNull
    V edgeValueOrDefault(EndpointPair<N> endpointPair, @CheckForNull V v);

    @CheckForNull
    V edgeValueOrDefault(N n2, N n3, @CheckForNull V v);

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> edges();

    boolean equals(@CheckForNull Object obj);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    @Override // com.google.common.graph.BaseGraph
    boolean hasEdgeConnecting(N n2, N n3);

    int hashCode();

    @Override // com.google.common.graph.BaseGraph
    int inDegree(N n2);

    @Override // com.google.common.graph.BaseGraph
    ElementOrder<N> incidentEdgeOrder();

    @Override // com.google.common.graph.BaseGraph
    Set<EndpointPair<N>> incidentEdges(N n2);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    boolean isDirected();

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    ElementOrder<N> nodeOrder();

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.Graph
    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N n2);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    Set<N> predecessors(N n2);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    Set<N> successors(N n2);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((ValueGraph<N, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((ValueGraph<N, V>) obj);
    }
}
