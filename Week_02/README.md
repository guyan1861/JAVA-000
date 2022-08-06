## weeek02 作业 
### 1.运行课上的例子，以及 netty 的例子,分析相关现象

在 MacPro 环境上运行测试盈利，使用 wrk 测试工具测试，有 sleep(20)的程序，wrk 测试结果全部返回请求数为0
删除掉这行代码之后，测试结果正常。原因还不是很清楚，猜测为 wrk 工具的原因，为了加快测试，可能没有等服务器响应就直接断开连接。

在 Windows 上使用 sb 工具得出结果。

BIO模式下，第一种测试结果最慢，原因如下
> 程序是单线程 串行程序 睡眠时间20ms

改为多线程后，程序的吞吐量上升，处理的请求数增加

改为线程池后，程序的性能得到提升，但是相对于多线程来说幅度有限，20%的提升。

接下来看 Netty 改进后的版本：

```bash
 wrk -t8 -c40 -d60s  http://localhost:8808/test
Running 1m test @ http://localhost:8808/test
  8 threads and 40 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   491.37us    3.09ms 168.49ms   99.60%
    Req/Sec    14.17k     1.29k   28.98k    85.02%
  6773462 requests in 1.00m, 704.10MB read
Requests/sec: 112722.92
Transfer/sec:     11.72MB
```

用 Netty 改进后性能提升非常明显，提升了好几倍。
