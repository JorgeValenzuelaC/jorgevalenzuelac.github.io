---
title: "On the Limits of Quantum Multiparty Simultaneous Communication"
collection: publications
category: preprints
permalink: /publication/2026-exponential-smp
excerpt: 'We establish an exponential separation between the public-coin and quantum Simultaneous Message Passing (SMP) models for a natural k-party relational problem.'
venue: 'Preprint'
paperurl: https://arxiv.org/abs/2609.10289
citation: 'Montealegre, P., Rapaport, I., &amp; Valenzuela, J. (2026). &quot;Quantum Communication Can Be Exponentially Weaker than Public Coins in Multiparty SMP.&quot; Preprint.'
---

The Simultaneous Message Passing (SMP) model provides a fundamental framework for comparing classical and quantum communication. For two players, Gavinsky et al. (STOC 2006) established a separation underlying the incomparability of shared randomness and quantum communication: \textsc{Index Coordination} needs *O(\log n)* public-coin bits but *\Omega(n^{1/3})* bounded-error qubits.

In this work, we establish a multiparty exponential separation through *\operatorname{IC}_{k,n}*, a natural *k*-party generalization of \textsc{Index Coordination}. Public-coin protocols solve it unambiguously with maximum message length *O(\log n)* bits. In contrast, quantum SMP protocols without shared entanglement or public coins require maximum message length *\Omega(n^{1-1/k})* qubits in the unambiguous regime and *\Omega(n^{(k-1)/(k+1)})* qubits in the bounded-error regime. A classical private-coin protocol matches the unambiguous bound, so quantum communication provides no asymptotic advantage over private randomness in this regime. For fixed error parameters, all constants are independent of *k*, establishing the exponential separation for every integer-valued function *k=k(n)\ge2*, without restricting its growth. Both quantum lower bounds become *\Omega(n)* when *k\ge c\log n* for any fixed *c>0*, matching the full-input protocol and yielding tight linear complexity in both regimes.

Our results demonstrate that quantum superposition cannot efficiently simulate the coordination afforded by public randomness, extending this separation to arbitrary *k*. To bound success probabilities for multiparty product states, we prove an exact factorization theorem for unambiguous quantum state identification, which may be of independent mathematical interest.

Joint work with Pedro Montealegre and Ivan Rapaport.
