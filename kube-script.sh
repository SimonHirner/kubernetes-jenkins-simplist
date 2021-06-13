# Reset Minikube
minikube stop;
minikube delete;
sudo rm -rf ~/.minikube;
sudo rm -rf ~/.kub;

# Start Minikube
minikube start --memory 8000 --cpus 2 --insecure-registry="192.168.49.2:30400";
minikube addons enable heapster;
minikube addons enable ingress;

# Deploy Registry
kubectl apply -f ./manifests/registry.yaml;
kubectl rollout status deployments/registry;

# Build and push Jenkins Dockerimage
docker build -t 192.168.49.2:30400/jenkins:latest -f ./jenkins/Dockerfile ./jenkins;
docker push 192.168.49.2:30400/jenkins:latest;

# Deploy Jenkins
kubectl apply -f ./manifests/jenkins.yaml;
kubectl rollout status deployment/jenkins;

# Get Jenkins Admin Password
sleep 8
kubectl exec -it `kubectl get pods --selector=app=jenkins --output=jsonpath={.items..metadata.name}` cat /var/jenkins_home/secrets/initialAdminPassword;
