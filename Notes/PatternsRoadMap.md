## SYSTEM DESIGN (LLD → HLD) ROADMAP
### Pattern-First Approach

#### These 10 patterns are chosen intentionally—they map directly to real distributed system concepts.

Phase 1: Object Creation & Ownership (Foundational Control)

Singleton → Global coordination, shared resources

Factory Method → Object creation abstraction

Abstract Factory → Platform-level extensibility

Builder → Complex object construction

Phase 2: Behavior & Decision Making

Strategy → Runtime behavior switching

Template Method → Standardized workflows

Command → Decoupled execution & queues

Phase 3: Communication & Eventing

Observer → Event-driven systems

Mediator → Centralized orchestration

Chain of Responsibility → Pipelines & filters