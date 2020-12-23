echo "Starting to deploy docker image.."
DOCKER_IMAGE=jesusmatosp/microservice-cliente-intercorp
TAG=$1
docker pull $DOCKER_IMAGE:$TAG
docker ps -q --filter ancestor=$DOCKER_IMAGE | xargs -r docker stop
docker run -d -p 8080:8080 $DOCKER_IMAGE:$TAG