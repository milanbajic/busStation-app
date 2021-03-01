import React from "react";
import { Jumbotron } from "react-bootstrap";

class Home extends React.Component {
	render() {
		let username = window.localStorage.getItem("username");
		return (
			<div>
				<Jumbotron>
					<h1 style={{ textAlign: "center" }}>Welcome, {username}</h1>
				</Jumbotron>
			</div>
		);
	}
}

export default Home;
