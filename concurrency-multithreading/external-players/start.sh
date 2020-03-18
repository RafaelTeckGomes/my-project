#!/bin/bash

java -jar target/externalPlayersMessage.jar player2 http://localhost:8090 &

sleep 1

java -jar target/externalPlayersMessage.jar player1 http://localhost:8091 &
