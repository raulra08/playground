
![][header-img]
# Kubernetes Deployment

You'll learn how deploy 2 instances of the same microservice running in a [docker][docker] container and that is deployed by [Kubernetes][kubernetes].

There's a complimentary blog [post][post-url] about this work.

## Build the docker image

```
docker build -t raulzuk/balance-service:v1 .
docker login
docker push raulzuk/balance-service:v1
```
## Create a Kubernetes cluster

### Install Kops

We start by setting up a a gossip-based Kubernetes cluster on AWS using [KOPS][kops], a command line tool for administering Kubernetes clusters.

### Set up your AWS account

Configure your [AWS account][aws-kops-iam] for kops. Skip the DNS configuration that you might find in the kops repo.

### Cluster State storage

There are [instructions][aws-kops-bucket] on how to store the state of your cluster

```
aws s3api create-bucket --bucket balance-service-com-state-store --create-bucket-configuration LocationConstraint=eu-west-1
aws s3api put-bucket-versioning --bucket balance-service-com-state-store --versioning-configuration Status=Enabled
```

In order set up a gossip-based cluster suffix it's name with `.k8s.local`

### Create the cluster

```
export NAME=balance-service.k8s.local
export KOPS_STATE_STORE=s3://balance-service-com-state-store
```

There are [instructions][aws-kops-cluster] for creating a cluster in AWS (one difference is that we deployed into the eu-west-1 region). But here's how we did it.

```
kops create cluster --zones eu-west-1a $NAME
```

### Build the cluster

```
kops update cluster $NAME --yes
```

You should see your instances being built in the EC2 Dashboard. Once they are ready you can validate the cluster build.

At this point you should be able to see the kubernetes master and 2 nodes instances running in AWS. Wait for them to be ready before getting a successful response from the command below

```
kops validate cluster $NAME
```

## Deploy the balance-service pod in a Kube node.

```
kubectl create -f balance-service-deployment.yml
```

## Deploy the kong pod (with postgres )

To protect our API we will use [kong][kong] API gateway. Luckily kong has prepared a kong pod and steps to deploy their API gateway in a kube pod; there are steps to [install][kong-kubernetes] kong in AWS since we already have a Kubernetes cluster then skip all steps to step 3. We have all the files mentioned in the kong deployment steps.

```
kubectl create -f postgres.yaml
kubectl create -f kong_migration_postgres.yaml
kubectl create -f kong_postgres.yaml
```

## Configure the API Gateway

```
$ curl -X POST --url http://a07358027a46011e7a5310678c5677ce-1334815499.eu-west-1.elb.amazonaws.com:8001/apis/ --data 'name=balance-service' --data 'methods=GET' --data 'upstream_url=http://balance-service:8080'
```

```
$ curl -X POST --url http://a07358027a46011e7a5310678c5677ce-1334815499.eu-west-1.elb.amazonaws.com:8001/apis/balance-service/plugins --data 'name=rate-limiting' --data 'config.second=100'
```

## Calling the balance service

```
$ curl -v http://a070b2710a46011e7a5310678c5677ce-1338563272.eu-west-1.elb.amazonaws.com:8000/balance
```
[post-url]: https://www.zuehlke.com/blog/en/even-your-boss-can-deploy-microservices/
[aws-kops-iam]: https://github.com/kubernetes/kops/blob/master/docs/aws.md#aws
[aws-kops-bucket]: https://github.com/kubernetes/kops/blob/master/docs/aws.md#cluster-state-storage
[aws-kops-cluster]: https://github.com/kubernetes/kops/blob/master/docs/aws.md#creating-your-first-cluster
[kong]: https://getkong.org/
[kong-kubernetes]: https://github.com/Mashape/kong-dist-kubernetes
[kops]: https://github.com/kubernetes/kops
[kubernetes]: https://kubernetes.io/
[docker]: https://www.docker.com/
[header-img]: img/header.png
