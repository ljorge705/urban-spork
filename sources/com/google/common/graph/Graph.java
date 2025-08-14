package com.google.common.graph;

import com.google.errorprone.annotations.DoNotMock;
import java.util.Set;
import javax.annotation.CheckForNull;

@DoNotMock("Use GraphBuilder to create a real instance")
@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
public interface Graph<N> extends BaseGraph<N> {
    Set<N> adjacentNodes(N n2);

    boolean allowsSelfLoops();

    @Override // com.google.common.graph.BaseGraph
    int degree(N n2);

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

    boolean isDirected();

    ElementOrder<N> nodeOrder();

    Set<N> nodes();

    @Override // com.google.common.graph.BaseGraph
    int outDegree(N n2);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.PredecessorsFunction, com.google.common.graph.Graph
    Set<N> predecessors(N n2);

    @Override // com.google.common.graph.BaseGraph, com.google.common.graph.SuccessorsFunction, com.google.common.graph.Graph
    Set<N> successors(N n2);

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable predecessors(Object obj) {
        return predecessors((Graph<N>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* bridge */ /* synthetic */ default Iterable successors(Object obj) {
        return successors((Graph<N>) obj);
    }
}
