package com.google.common.graph;

import java.util.Set;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
interface BaseGraph<N> extends SuccessorsFunction<N>, PredecessorsFunction<N> {
    Set<N> adjacentNodes(N n2);

    boolean allowsSelfLoops();

    int degree(N n2);

    Set<EndpointPair<N>> edges();

    boolean hasEdgeConnecting(EndpointPair<N> endpointPair);

    boolean hasEdgeConnecting(N n2, N n3);

    int inDegree(N n2);

    ElementOrder<N> incidentEdgeOrder();

    Set<EndpointPair<N>> incidentEdges(N n2);

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    int outDegree(N n2);

    Set<N> predecessors(N n2);

    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    Set<N> successors(N n2);

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((BaseGraph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((BaseGraph<N>) obj);
    }
}
