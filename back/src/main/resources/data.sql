INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');
              
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (1, 'Lasta', 'Nemanjina 53', '12345678');
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (2, 'Adio Turs', 'Svetosavska 9', '24681357');
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (3, 'Auto Kodeks', 'Cara Lazara 6', '87654321');
INSERT INTO prevoznik (id, naziv, adresa, pib) VALUES (4, 'Lotus Travel', 'Kosovska 37', '13572468');

INSERT INTO linija (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES (1, 10,  600, '07:15', 'Kikinda', 1);
INSERT INTO linija (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES (2, 3,  1200, '09:15', 'Beograd', 2);
INSERT INTO linija (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES (3, 15,  850, '09:00', 'Novi Sad', 3);
INSERT INTO linija (id, broj_mesta, cena_karte, vreme_polaska, destinacija, prevoznik_id) VALUES (4, 10,  650, '12:00', 'Kragujevac', 4);

INSERT INTO rezervacija (id, linija_id) VALUES (1, 1);