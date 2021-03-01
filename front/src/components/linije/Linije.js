import React from "react";
import Axios from "../../apis/Axios";
import { Form, Button, Table, ButtonGroup } from "react-bootstrap";

class Linije extends React.Component {
	constructor(props) {
		super(props);

		let rezervacija = {
			linijaId: -1,
		};

		let linija = {
			brojMesta: 0,
			cenaKarte: 0,
			vremePolaska: "",
			destinacija: "",
			prevoznikId: -1,
		};

		this.state = {
			rezervacija: rezervacija,
			linija: linija,
			linije: [],
			prevoznici: [],
			search: { destinacija: "", cenaKarte: 0, prevoznikId: -1 },
			pageNum: 0,
			totalPages: 1,
		};
	}

	componentDidMount() {
		this.getData();
	}

	async getData() {
		await this.getLinije();
		await this.getPrevoznici();
	}

	async getLinije(page = null) {
		let config = { params: {} };

		if (this.state.search.destinacija != "") {
			config.params.destinacija = this.state.search.destinacija;
		}

		if (this.state.search.cenaKarte != 0) {
			config.params.cenaKarte = this.state.search.cenaKarte;
		}

		if (this.state.search.prevoznikId != -1) {
			config.params.prevoznikId = this.state.search.prevoznikId;
		}

		if (page != null) {
			config.params.pageNum = page;
		} else {
			config.params.pageNum = this.state.pageNum;
		}

		try {
			let result = await Axios.get("/linije", config);
			if (result && result.status === 200) {
				this.setState({
					linije: result.data,
					totalPages: result.headers["total-pages"],
				});
			}
		} catch (error) {
			alert("Greška prilikom dobavljanja linija.");
		}
	}

	async getPrevoznici() {
		try {
			let result = await Axios.get("/prevoznici");
			if (result && result.status === 200) {
				this.setState({ prevoznici: result.data });
			}
		} catch (error) {
			alert("Greška prilikom dobavljanja prevoznika.");
		}
	}

	goToEdit(linijaId) {
		this.props.history.push("/linije/edit/" + linijaId);
	}

	async doAdd() {
		try {
			await Axios.post("/linije/", this.state.linija);
			let linija = {
				brojMesta: 0,
				cenaKarte: 0,
				vremePolaska: "",
				destinacija: "",
				prevoznikId: -1,
			};
			this.setState({ linija: linija });
			this.getLinije();
		} catch (error) {
			alert("Greška prilikom dodavanja linije.");
		}
	}

	async doDelete(linijaId) {
		try {
			await Axios.delete("/linije/" + linijaId);
			this.getLinije();
		} catch (error) {
			alert("Greška prilikom brisanja linije.");
		}
	}

	async rezervisi(linijaId) {
		let rezervacija = this.state.rezervacija;
		rezervacija["linijaId"] = linijaId;
		this.setState({ rezervacija: rezervacija });

		try {
			await Axios.post("/rezervacije/", this.state.rezervacija);
			let rezervacija = {
				linijaId: -1,
			};
			this.setState({ rezervacija: rezervacija });
			this.getLinije();
			alert("Rezervacija uspešno kreirana!");
		} catch (error) {
			alert("Rezervacija nije uspela.");
		}
	}

	addHandleChange(e) {
		let input = e.target;

		let name = input.name;
		let value = input.value;

		let linija = this.state.linija;
		linija[name] = value;

		this.setState({ linija: linija });
	}

	searchHandleChange(e) {
		let input = e.target;

		let name = input.name;
		let value = input.value;

		let search = this.state.search;
		search[name] = value;

		this.setState({ search: search });
	}

	doSearch() {
		this.setState({ totalPages: 1, pageNum: 0 });
		this.getLinije(0);
	}

	changePage(direction) {
		let page = this.state.pageNum + direction;
		this.getLinije(page);
		this.setState({ pageNum: page });
	}

	render() {
		return (
			<div>
				<h1>Autobuska stanica</h1>
				<h3 style={{ marginTop: 15 }}>Dodavanje linije</h3>
				<Form>
					<Form.Group>
						<Form.Label>Broj mesta</Form.Label>
						<Form.Control
							onChange={(e) => this.addHandleChange(e)}
							name="brojMesta"
							value={this.state.linija.brojMesta}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Cena karte</Form.Label>
						<Form.Control
							onChange={(e) => this.addHandleChange(e)}
							name="cenaKarte"
							value={this.state.linija.cenaKarte}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Destinacija</Form.Label>
						<Form.Control
							onChange={(e) => this.addHandleChange(e)}
							name="destinacija"
							value={this.state.linija.destinacija}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Vreme polaska</Form.Label>
						<Form.Control
							onChange={(e) => this.addHandleChange(e)}
							name="vremePolaska"
							value={this.state.linija.vremePolaska}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Prevoznik</Form.Label>
						<Form.Control
							onChange={(e) => this.addHandleChange(e)}
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
					<Button variant="success" onClick={() => this.doAdd()}>
						Dodaj
					</Button>
				</Form>
				<h3 style={{ marginTop: 15 }}>Pretraga linija</h3>
				<Form>
					<Form.Group>
						<Form.Label>Destinacija</Form.Label>
						<Form.Control
							onChange={(e) => this.searchHandleChange(e)}
							name="destinacija"
							value={this.state.search.destinacija}
							as="input"
						></Form.Control>
					</Form.Group>
					<Form.Group>
						<Form.Label>Prevoznik</Form.Label>
						<Form.Control
							onChange={(e) => this.searchHandleChange(e)}
							name="prevoznikId"
							value={this.state.search.prevoznikId}
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
					<Form.Group>
						<Form.Label>Maksimalna cena</Form.Label>
						<Form.Control
							onChange={(e) => this.searchHandleChange(e)}
							name="cenaKarte"
							value={this.state.search.cenaKarte}
							as="input"
						></Form.Control>
					</Form.Group>
					<Button variant="info" onClick={() => this.doSearch()}>
						Pretraga
					</Button>
				</Form>
				<div style={{ marginTop: 25, float: "right" }}>
					<ButtonGroup>
						<Button
							disabled={this.state.pageNum == 0}
							onClick={() => this.changePage(-1)}
						>
							Prethodna
						</Button>
						<Button
							disabled={this.state.pageNum + 1 == this.state.totalPages}
							onClick={() => this.changePage(1)}
						>
							Sledeća
						</Button>
					</ButtonGroup>
				</div>
				<Table bordered striped style={{ marginTop: 5 }}>
					<thead className="thead-dark">
						<tr>
							<th>Naziv prevoznika</th>
							<th>Destinacija</th>
							<th>Broj mesta</th>
							<th>Vreme polaska</th>
							<th>Cena karte</th>
							<th colSpan={2}>Opcije</th>
						</tr>
					</thead>
					<tbody>
						{this.state.linije.map((linija) => {
							return (
								<tr key={linija.id}>
									<td>{linija.prevoznikNaziv}</td>
									<td>{linija.destinacija}</td>
									<td>{linija.brojMesta}</td>
									<td>{linija.vremePolaska}</td>
									<td>{linija.cenaKarte}</td>
									<td>
										<Button
											variant="success"
											onClick={() => this.rezervisi(linija.id)}
											style={{ marginLeft: 5 }}
										>
											Rezerviši
										</Button>
										<Button
											variant="warning"
											onClick={() => this.goToEdit(linija.id)}
											style={{ marginLeft: 5 }}
										>
											Izmeni
										</Button>
										<Button
											variant="danger"
											onClick={() => this.doDelete(linija.id)}
											style={{ marginLeft: 5 }}
										>
											Obriši
										</Button>
									</td>
								</tr>
							);
						})}
					</tbody>
				</Table>
			</div>
		);
	}
}

export default Linije;
