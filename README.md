# quote-pattern-match-benchmark

https://github.com/scala/scala3/issues/22432

```
[info] running (fork) org.openjdk.jmh.Main -i 10 -f1 -t1
[info] # JMH version: 1.37
[info] # VM version: JDK 21.0.5, OpenJDK 64-Bit Server VM, 21.0.5+11-LTS
[info] # VM invoker: /usr/lib/jvm/temurin-21-jdk-amd64/bin/java
[info] # VM options: <none>
[info] # Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
[info] # Warmup: 5 iterations, 10 s each
[info] # Measurement: 10 iterations, 10 s each
[info] # Timeout: 10 min per iteration
[info] # Threads: 1 thread, will synchronize iterations
[info] # Benchmark mode: Throughput, ops/time
[info] # Benchmark: example.Main.lowLevelReflection
[info] # Run progress: 0.00% complete, ETA 00:05:00
[info] # Fork: 1 of 1
[info] # Warmup Iteration   1: 0.090 ops/s
[info] # Warmup Iteration   2: 0.265 ops/s
[info] # Warmup Iteration   3: 0.333 ops/s
[info] # Warmup Iteration   4: 0.336 ops/s
[info] # Warmup Iteration   5: 0.341 ops/s
[info] Iteration   1: 0.349 ops/s
[info] Iteration   2: 0.345 ops/s
[info] Iteration   3: 0.341 ops/s
[info] Iteration   4: 0.348 ops/s
[info] Iteration   5: 0.350 ops/s
[info] Iteration   6: 0.344 ops/s
[info] Iteration   7: 0.345 ops/s
[info] Iteration   8: 0.347 ops/s
[info] Iteration   9: 0.338 ops/s
[info] Iteration  10: 0.352 ops/s
[info] Result "example.Main.lowLevelReflection":
[info]   0.346 ±(99.9%) 0.006 ops/s [Average]
[info]   (min, avg, max) = (0.338, 0.346, 0.352), stdev = 0.004
[info]   CI (99.9%): [0.340, 0.352] (assumes normal distribution)
[info] # JMH version: 1.37
[info] # VM version: JDK 21.0.5, OpenJDK 64-Bit Server VM, 21.0.5+11-LTS
[info] # VM invoker: /usr/lib/jvm/temurin-21-jdk-amd64/bin/java
[info] # VM options: <none>
[info] # Blackhole mode: compiler (auto-detected, use -Djmh.blackhole.autoDetect=false to disable)
[info] # Warmup: 5 iterations, 10 s each
[info] # Measurement: 10 iterations, 10 s each
[info] # Timeout: 10 min per iteration
[info] # Threads: 1 thread, will synchronize iterations
[info] # Benchmark mode: Throughput, ops/time
[info] # Benchmark: example.Main.quoteMatch
[info] # Run progress: 50.00% complete, ETA 00:02:54
[info] # Fork: 1 of 1
[info] # Warmup Iteration   1: 0.036 ops/s
[info] # Warmup Iteration   2: 0.049 ops/s
[info] # Warmup Iteration   3: 0.051 ops/s
[info] # Warmup Iteration   4: 0.053 ops/s
[info] # Warmup Iteration   5: 0.054 ops/s
[info] Iteration   1: 0.051 ops/s
[info] Iteration   2: 0.053 ops/s
[info] Iteration   3: 0.053 ops/s
[info] Iteration   4: 0.053 ops/s
[info] Iteration   5: 0.052 ops/s
[info] Iteration   6: 0.050 ops/s
[info] Iteration   7: 0.052 ops/s
[info] Iteration   8: 0.053 ops/s
[info] Iteration   9: 0.051 ops/s
[info] Iteration  10: 0.049 ops/s
[info] Result "example.Main.quoteMatch":
[info]   0.052 ±(99.9%) 0.002 ops/s [Average]
[info]   (min, avg, max) = (0.049, 0.052, 0.053), stdev = 0.002
[info]   CI (99.9%): [0.049, 0.054] (assumes normal distribution)
[info] # Run complete. Total time: 00:07:53
[info] REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
[info] why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
[info] experiments, perform baseline and negative tests that provide experimental control, make sure
[info] the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
[info] Do not assume the numbers tell you what you want them to tell.
[info] NOTE: Current JVM experimentally supports Compiler Blackholes, and they are in use. Please exercise
[info] extra caution when trusting the results, look into the generated code to check the benchmark still
[info] works, and factor in a small probability of new VM bugs. Additionally, while comparisons between
[info] different JVMs are already problematic, the performance difference caused by different Blackhole
[info] modes can be very significant. Please make sure you use the consistent Blackhole mode for comparisons.
[info] Benchmark                 Mode  Cnt  Score   Error  Units
[info] Main.lowLevelReflection  thrpt   10  0.346 ± 0.006  ops/s
[info] Main.quoteMatch          thrpt   10  0.052 ± 0.002  ops/s
```
