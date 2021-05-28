FROM node:16-alpine

WORKDIR /appfront

ENV PATH /app/node_modules/.bin:$PATH

# RUN npm install -g npm

COPY ./package.json ./

RUN npm install 

RUN npm install -g @vue/cli

COPY ./ ./

# RUN rm -rf node_modules package-lock.json

# ENV PATH="./frontend/node_modules/.bin:$PATH"

EXPOSE 8080

CMD ["npm", "run", "serve"]