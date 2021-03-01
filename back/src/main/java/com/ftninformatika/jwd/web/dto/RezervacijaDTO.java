package com.ftninformatika.jwd.web.dto;

public class RezervacijaDTO {

    private Long id;

    private Long linijaId;

    public RezervacijaDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}
}