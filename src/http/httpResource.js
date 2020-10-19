import axios from "axios";

const isHandlerEnabled = (config = {}) => {
  // eslint-disable-next-line no-prototype-builtins
  return config.hasOwnProperty("handlerEnabled") && !config.handlerEnabled
    ? false
    : true;
};

// axiosInstance.get('/v2/api-endpoint', { handlerEnabled: false })

const instance = axios.create({
  baseURL: 'http://localhost:7100',
  withCredentials: true,
  timeout: 10000
});

const requestHandler = request => {
  if (isHandlerEnabled(request)) {
    console.log("Request Interceptor", request);
  }
  return request;
};

const errorHandler = error => {
  if (isHandlerEnabled(error.config)) {
    console.log("Error Interceptor", error);

    if (error.response) {
      if (error.response.status === 401) {
        // eslint-disable-next-line no-undef
        performLogout();
      }
    }
  }
  return Promise.reject({ ...error });
};

const successHandler = response => {
  if (isHandlerEnabled(response.config)) {
    console.log("Response Interceptor", response);
  }
  return response;
};

instance.interceptors.request.use(request => requestHandler(request));

instance.interceptors.response.use(
  response => successHandler(response),
  error => errorHandler(error)
);

export default instance;
