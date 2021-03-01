import React from "react";
import { login } from "../../services/auth";
import { Jumbotron, Form, Button } from "react-bootstrap";

class Login extends React.Component {
	constructor() {
		super();
		this.state = { username: "", password: "" };
	}

	handleChange(e) {
		let input = e.target;

		let name = input.name;
		let value = input.value;

		let change = {};

		change[name] = value;

		this.setState(change);
	}

	doLogin(e) {
		e.preventDefault();
		login(this.state.username, this.state.password);
	}

	render() {
		return (
			<div>
				<Jumbotron>
					<h1 style={{ textAlign: "center" }}>Welcome!</h1>
					<Form>
						<Form.Group>
							<Form.Label>Username</Form.Label>
							<Form.Control
								style={{ textAlign: "center" }}
								name="username"
								onChange={(e) => {
									this.handleChange(e);
								}}
							/>
						</Form.Group>
						<Form.Group>
							<Form.Label>Password</Form.Label>
							<Form.Control
								style={{ textAlign: "center" }}
								name="password"
								type="password"
								onChange={(e) => {
									this.handleChange(e);
								}}
							/>
						</Form.Group>
						<Button
							variant="outline-info"
							onClick={(e) => {
								this.doLogin(e);
							}}
						>
							Log in
						</Button>
					</Form>
				</Jumbotron>
			</div>
		);
	}
}

export default Login;
