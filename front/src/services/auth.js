import Axios from "../apis/Axios";
import jwtDecode from "jwt-decode";

export const login = async function (username, password) {
	let dto = {
		username: username,
		password: password,
	};

	try {
		let result = await Axios.post("/korisnici/auth", dto);
		window.localStorage.setItem("token", result.data);
		window.localStorage.setItem("username", jwtDecode(result.data).sub);
		window.location.reload();
	} catch (error) {
		alert("Prijavljivanje nije uspelo. Pokušajte ponovo.");
	}
};

export const logout = function () {
	window.localStorage.removeItem("token");
	window.location.reload();
};
