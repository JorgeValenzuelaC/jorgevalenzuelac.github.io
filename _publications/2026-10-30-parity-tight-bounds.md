---
title: "Tight Bounds for Anonymous Parity Certification"
collection: publications
category: preprints
permalink: /publication/2026-parity-tight
excerpt: 'We prove tight bounds for the problem of certifying parity in the anonymous model with radius 1'
venue: 'Preprint'
paperurl: https://arxiv.org/abs/2610.38829
citation: 'Montealegre, P., &amp; Valenzuela, J. (2026). &quot;Tight Bounds for Anonymous Parity Certification.&quot; Preprint.'
---

Local certification is the task of verifying graph properties using certificates assigned to vertices and checked using only local information. A central question is which properties admit constant-size certificates, independently of network size. A fundamental test case is the parity of the network order. Bousquet, Feuilloley, Valenzuela, and Zeitoun (DISC 2026) showed that the complexity of certifying even order depends dramatically on the verification model. On arbitrary graphs, three bits per vertex suffice with unique identifiers and verification radius two. In the anonymous radius-one model, they established an Ω(loglog∗n) lower bound and an O(logn) upper bound. They also showed that constant-size certificates suffice in this model on bounded-expansion classes.
In this paper, we settle the complexity of anonymous parity certification on general graphs and further investigate the role of graph sparsity. Our main contributions are:
* We close the gap left by Bousquet et al., proving that the local complexity of certifying even order in the anonymous radius-one model is Θ(loglogn) bits.
* We show that on graphs of degeneracy at most d, parity can be certified with O(logd) bits, while Ω(loglogd) bits are necessary. In particular, every class of bounded degeneracy admits constant-size certificates, strictly generalizing the result for bounded-expansion classes.
* We show that constant-size certification cannot be guaranteed on nowhere-dense classes, a broader notion of sparsity that includes bounded expansion. We construct a fixed, subgraph-closed nowhere-dense class on which parity certification requires Θ(loglogn) bits.
Together, our results provide a more complete picture of anonymous parity certification on sparse graph classes, showing that its complexity depends on the particular structural notion of sparsity. 

Joint work with Pedro Montealegre.
