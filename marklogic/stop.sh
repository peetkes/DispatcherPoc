#!/usr/bin/env sh
pushd docker

source .env
docker compose -p $DOCKERPROJECT stop

popd