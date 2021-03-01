import React from "react";
import Axios from "../../apis/Axios";
import { Button, Form } from "react-bootstrap";

class EditLinija extends React.Component {
	constructor(props) {
		super(props);

		let linija = {
			brojMesta: 0,
			cenaKarte: 0,
			vremePolaska: "",
			destinacija: "",
			prevoznikId: -1,
		};

		this.state = { linija: linija, prevoznici: [] };
	}

	componentDidMount() {
		this.getData();
	}

	async getData() {
		await this.getLinija();
		await this.getPrevoznici();
	}

	async getLinija() {
		try {
			let result = await Axios.get("/linije/" + this.props.match.params.id);
			if (result && result.status === 200) {
				this.setState({ linija: result.data });
			}
		} catch (error) {
			alert("Greška pri dobavljanju linija!");
		}
	}

	async getPrevoznici() {
		try {
			let result = await Axios.get("/prevoznici");
			if (result && result.status === 200) {
				this.setState({ prevoznici: result.data });
			}
		} catch (error) {
			alert("Greška pri dobavljanju prevoznika!");
		}
	}

	async doEdit() {
		try {
			await Axios.put(
				"/linije/" + this.props.match.params.id,
				this.state.linija
			);
			this.props.history.push("/linije");
			alert("Linija je uspešno izmenjena!");
		} catch (error) {
			alert("Greška! Pokušajte ponovo.");
		}
	}

	handleChange(e) {
		let input = e.target;

		let name = input.name;
		let value = input.value;

		let linija = this.state.linija;
		linija[name] = value;

		this.setState({ linija: linija });
	}

	render() {
		return (
			<div>
				<h1>Izmena linije</h1>
				<Form style={{ marginTop: 30 }}>
					<Form.Group>
						<Form.Label>Broj mesta</Form.Label>
						<Form.Control
							onChange={(e) => this.handleChange(e)}
							name="brojMesta"
							value={this.state.linija.brojMesta}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Cena karte</Form.Label>
						<Form.Control
							onChange={(e) => this.handleChange(e)}
							name="cenaKarte"
							value={this.state.linija.cenaKarte}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Destinacija</Form.Label>
						<Form.Control
							onChange={(e) => this.handleChange(e)}
							name="destinacija"
							value={this.state.linija.destinacija}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Vreme polaska</Form.Label>
						<Form.Control
							onChange={(e) => this.handleChange(e)}
							name="vremePolaska"
							value={this.state.linija.vremePolaska}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Prevoznik</Form.Label>
						<Form.Control
							onChange={(e) => this.handleChange(e)}
							name="prevoznikId"
							value={this.state.linija.prevoznikId}
							as="select"
						>
							<option value={-1}></option>
							{this.state.prevoznici.map((prevoznik) => {
								return (
									<option key={prevoznik.id} value={prevoznik.id}>
										{" "}
										{prevoznik.naziv}{" "}
									</option>
								);
							})}
						</Form.Control>
					</Form.Group>
					<Button variant="primary" onClick={() => this.doEdit()}>
						Izmeni
					</Button>
				</Form>
			</div>
		);
	}
}

export default EditLinija;
