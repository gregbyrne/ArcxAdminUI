# base image
FROM node:12-alpine

RUN apk add tree


WORKDIR /usr/src/app
COPY . /usr/src/app

RUN pwd
RUN tree .

RUN npm install
RUN npm install -g @vue/cli
RUN npm run build

ENV PATH /usr/src/app/node_modules/.bin:$PATH


EXPOSE 8080

# start app
CMD ["npm", "run", "serve"]
