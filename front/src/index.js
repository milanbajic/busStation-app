import React from "react";
import ReactDOM from "react-dom";
import {
	Route,
	Link,
	HashRouter as Router,
	Switch,
	Redirect,
} from "react-router-dom";
import Home from "./components/Home";
import Linije from "./components/linije/Linije";
import EditLinija from "./components/linije/EditLinija";
import NotFound from "./components/NotFound";
import Login from "./components/authentication/Login";
import { logout } from "./services/auth";

import { Navbar, Nav, Container, Button } from "react-bootstrap";

class App extends React.Component {
	render() {
		let token = window.localStorage.getItem("token");
		if (token) {
			return (
				<div>
					<Router>
						<Navbar bg="dark" variant="dark" expand fixed="top">
							<Navbar.Brand>
								<Link to="/">Home</Link>
							</Navbar.Brand>
							<Nav className="mr-auto">
								<Nav.Link as={Link} to="/linije">
									Autobuska stanica
								</Nav.Link>
							</Nav>
							<Button variant="outline-info" onClick={() => logout()}>
								Odjava
							</Button>
						</Navbar>

						<Container style={{ marginTop: 75 }}>
							<Switch>
								<Route exact path="/" component={Home} />
								<Route exact path="/linije" component={Linije} />
								<Route exact path="/linije/edit/:id" component={EditLinija} />
								<Route exact path="/login" render={() => <Redirect to="/" />} />
								<Route component={NotFound} />
							</Switch>
						</Container>
					</Router>
				</div>
			);
		} else {
			return (
				<Container>
					<Router>
						<Switch>
							<Route exact path="/login" component={Login} />
							<Route render={() => <Redirect to="/login" />} />
						</Switch>
					</Router>
				</Container>
			);
		}
	}
}

ReactDOM.render(<App />, document.getElementById("root"));
