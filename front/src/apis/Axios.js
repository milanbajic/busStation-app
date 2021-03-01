import axios from "axios";
import { logout } from "../services/auth";

var Axios = axios.create({
	baseURL: "http://localhost:8080/api",
});

Axios.interceptors.request.use(function success(config) {
	let token = window.localStorage.getItem("token");
	if (token) {
		config.headers["Authorization"] = `Bearer ${token}`;
	}

	return config;
});

Axios.interceptors.response.use(
	function success(response) {
		return response;
	},
	function failure(error) {
		let token = window.localStorage.getItem("token");
		if (token) {
			if (error.response && error.response.status === 403) {
				logout();
			}
		}

		throw error;
	}
);

export default Axios;
