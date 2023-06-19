# awsdevday2023tokyo-sls-framework-snapstart

## Result of K6

```
     data_received..................: 1.2 MB 430 kB/s
     data_sent......................: 168 kB 62 kB/s
     http_req_blocked...............: avg=226.63ms min=177.3ms  med=207.54ms max=325.76ms p(90)=290.28ms p(95)=301.46ms
     http_req_connecting............: avg=13.85ms  min=7.97ms   med=12.26ms  max=21.96ms  p(90)=18.89ms  p(95)=19.29ms 
     http_req_duration..............: avg=1.17s    min=934.48ms med=1.17s    max=1.51s    p(90)=1.28s    p(95)=1.31s   
       { expected_response:true }...: avg=1.17s    min=934.48ms med=1.17s    max=1.51s    p(90)=1.28s    p(95)=1.31s   
     http_req_failed................: 0.00%  ✓ 0         ✗ 200  
     http_req_receiving.............: avg=237.07µs min=7µs      med=20µs     max=1.62ms   p(90)=1.06ms   p(95)=1.24ms  
     http_req_sending...............: avg=49.9µs   min=20µs     med=33µs     max=2.41ms   p(90)=57µs     p(95)=67.05µs 
     http_req_tls_handshaking.......: avg=185.59ms min=141.08ms med=167.51ms max=289.85ms p(90)=246.08ms p(95)=255.46ms
     http_req_waiting...............: avg=1.17s    min=933.49ms med=1.17s    max=1.51s    p(90)=1.28s    p(95)=1.31s   
     http_reqs......................: 200    73.537766/s
     iteration_duration.............: avg=2.4s     min=2.19s    med=2.4s     max=2.71s    p(90)=2.5s     p(95)=2.54s   
     iterations.....................: 200    73.537766/s
     vus............................: 200    min=200     max=200
     vus_max........................: 200    min=200     max=200
```

## Result of CloudWatch Logs Insights

```
filter @message like "REPORT"
| filter @message not like "RESTORE_REPORT"
| filter @message like "Restore Duration"
| parse @message "Restore Duration:* ms" as restoreTime
| fields @duration + restoreTime as duration
| stats count(*) as count, pct(duration, 50) as p50, pct(duration, 90) as p90, pct(duration, 99) as p99, max(duration) as max, avg(duration) as avg_Duration, avg(restoreTime) as avg_RestoreTime, avg(@maxMemoryUsed)
```
---
| count | p50     | p90      | p99      | max    | avg_Duration | avg_RestoreTime | avg(@maxMemoryUsed) |
|-------|---------|----------|----------|--------|--------------|-----------------|---------------------|
| 200   | 509.222 | 578.1426 | 630.0364 | 735.93 | 506.6787     | 262.4065        | 134765000           |
---
