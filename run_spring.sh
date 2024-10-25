#!/bin/bash

if [[ ! -f .env ]]; then
    echo "Erro: O arquivo .env não foi encontrado."
    exit 1
fi

export $(grep -v '^#' .env | xargs)

./mvnw spring-boot:run
