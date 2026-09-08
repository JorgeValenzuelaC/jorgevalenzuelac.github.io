---
title: "On the Limits of Quantum Multiparty Simultaneous Communication"
collection: publications
category: preprints
permalink: /publication/2026-exponential-smp
excerpt: 'We establish an exponential separation between the public-coin and quantum Simultaneous Message Passing (SMP) models for a natural k-party generalization of Index Coordination.'
venue: 'Preprint'
paperurl: '/files/exponential.pdf'
citation: 'Montealegre, P., Rapaport, I., &amp; Valenzuela, J. (2026). &quot;Quantum Communication Can Be Exponentially Weaker than Public Coins in Multiparty SMP.&quot; Preprint.'
---

The Simultaneous Message Passing (SMP) model provides a fundamental framework for comparing the relative power of classical and quantum communication. In the two-player setting, Gavinsky et al. (STOC 2006) established the incomparability of shared randomness and quantum communication using \textsc{Index Coordination}, where public-coin protocols need only *O(\log n)* bits while bounded-error quantum protocols require *\Omega(n^{1/3})* qubits.

In this work, we establish a multiparty exponential separation by introducing *\operatorname{IC}_{k,n}*, a natural *k*-party generalization of \textsc{Index Coordination}. We show that public-coin protocols solve *\operatorname{IC}_{k,n}* unambiguously with maximum message length *O(\log n)*. In contrast, the maximum message length of every quantum SMP protocol without pre-shared entanglement or public coins is *\Omega(n^{1-1/k})* qubits in the unambiguous regime and *\Omega(n^{\frac{k-1}{k+1}})* qubits in the general bounded-error regime. The unambiguous lower bound is matched by a classical private-coin protocol with maximum message length *O(n^{1-1/k})*, uniformly in *k*. For fixed error parameters, the constants in these bounds are independent of *k*. Consequently, the exponential separation in maximum message length holds for every integer-valued function *k=k(n)\ge2*, without any restriction on its growth rate. Moreover, both quantum lower bounds become *\Omega(n)* when *k\ge c\log n* for any fixed *c>0*, matching the full-input protocol and establishing tight linear complexity in both error regimes.

Conceptually, our results demonstrate that the coordination afforded by public randomness cannot be efficiently simulated by quantum superposition, extending the exponential incomparability of these resources to arbitrary *k*. To overcome the complexities of bounding success probabilities for multiparty product states in these regimes, we prove an exact factorization theorem for unambiguous quantum state identification, which may be of independent mathematical interest.

Joint work with Pedro Montealegre and Iván Rapaport.
