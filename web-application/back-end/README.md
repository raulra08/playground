
### Balance Service Kubernetes Deployment
1) Build the docker image

```
docker build -t raulzuk/balance-service:v1 .
docker login
docker push raulzuk/balance-service:v1
```

2) Create a Kubernetes cluster

When following the readme file create a gossip-based cluster and skip DNS configuration steps

```
aws s3api create-bucket --bucket balance-service-com-state-store --create-bucket-configuration LocationConstraint=eu-west-1
aws s3api put-bucket-versioning --bucket balance-service-com-state-store --versioning-configuration Status=Enabled
```
```
export NAME=balance-service.k8s.local
export KOPS_STATE_STORE=s3://balance-service-com-state-store
```

2.1 Create the cluster

```
kops create cluster --zones eu-west-1a $NAME
```

2.2 Build the cluster
```
kops update cluster $NAME --yes
```

2.3 You should see your instances being built in the EC2 Dashboard. Once they are ready you can validate the cluster build.
```
kops validate cluster $NAME
```

3) Deploy the balance-service pod in a Kube node.

```
kubectl create -f balance-service-deployment.yml
```

I have included the service inside the yaml file so exposing the deployment is not needed anynmore.

4) Deploy the kong pod (with postgres ) to protect our API - https://github.com/Mashape/kong-dist-kubernetes
Since we are deploying in AWS and we already have a Kubernetes cluster then skip all steps to step 3.


5) Configure Kong

```
$ curl -X POST --url http://a07358027a46011e7a5310678c5677ce-1334815499.eu-west-1.elb.amazonaws.com:8001/apis/ --data 'name=balance-service' --data 'methods=GET' --data 'upstream_url=http://balance-service:8080'
{"created_at":1506612250000,"strip_uri":true,"id":"47a13b23-2ea3-4828-ab85-eaa5ba5ccf7a","name":"balance-service","http_if_terminated":false,"preserve_host":false,"upstream_url":"http:\/\/balance-service:8080","methods":["GET"],"upstream_send_timeout":60000,"upstream_connect_timeout":60000,"upstream_read_timeout":60000,"retries":5,"https_only":false}
```

```
$ curl -X POST --url http://a07358027a46011e7a5310678c5677ce-1334815499.eu-west-1.elb.amazonaws.com:8001/apis/balance-service/plugins --data 'name=rate-limiting' --data 'config.second=100'
{"created_at":1506621419000,"config":{"second":100,"redis_database":0,"policy":"cluster","hide_client_headers":false,"redis_timeout":2000,"redis_port":6379,"limit_by":"consumer","fault_tolerant":true},"id":"4d5babd7-bec2-4241-92c3-002aea2fe78d","name":"rate-limiting","api_id":"47a13b23-2ea3-4828-ab85-eaa5ba5ccf7a","enabled":true}
```

6) Calling the balance service

```
$ curl -v http://a070b2710a46011e7a5310678c5677ce-1338563272.eu-west-1.elb.amazonaws.com:8000/balance | json_pp
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying 34.251.102.173...
* TCP_NODELAY set
* Connected to a070b2710a46011e7a5310678c5677ce-1338563272.eu-west-1.elb.amazonaws.com (34.251.102.173) port 8000 (#0)
> GET /balance HTTP/1.1
> Host: a070b2710a46011e7a5310678c5677ce-1338563272.eu-west-1.elb.amazonaws.com:8000
> User-Agent: curl/7.54.0
> Accept: */*
>
< HTTP/1.1 200 OK
< Content-Type: application/json
< Content-Length: 30
< Connection: keep-alive
< Date: Thu, 28 Sep 2017 17:51:29 GMT
< Vary: Accept-Encoding
< X-Kong-Upstream-Latency: 3
< X-Kong-Proxy-Latency: 1
< Via: kong/0.11.0
<
* Connection #0 to host a070b2710a46011e7a5310678c5677ce-1338563272.eu-west-1.elb.amazonaws.com left intact
{
   "currency" : "GBP",
   "amount" : 98
}
```