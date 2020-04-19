NAME=hello-cloudrun-clj
PROJECT=YOUR-PROJECT
REVISION := $(shell git rev-parse --short HEAD)

build:
	lein ring uberwar
	docker build -t $(NAME) .

run: build
	docker run --rm --name $(NAME) -p 8080:8080 $(NAME)

push: build
	docker tag $(NAME) gcr.io/$(PROJECT)/$(NAME):$(REVISION)
	docker push gcr.io/$(PROJECT)/$(NAME):$(REVISION)

deploy: push
	gcloud config set run/region us-central1
	gcloud run deploy $(NAME) --image gcr.io/$(PROJECT)/$(NAME):$(REVISION) --allow-unauthenticated --platform managed --revision-suffix $(REVISION)
