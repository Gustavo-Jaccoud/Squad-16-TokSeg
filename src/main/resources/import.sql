INSERT INTO public.users VALUES ('e1b1d8a7-cdbf-4d00-853e-76c54d173c89', 'gustavo@gmail.com', 'Gustavo', '$2a$10$n8O8JQOQY9NriJiEF1kaOuyZCYHD8/TgtJLXuGaTfwUU0HWaYJddy', 'ADMIN', '1234567890');

INSERT INTO public.users VALUES ('097b4df6-8049-404b-b94e-4bd1d438374e', 'joao.teste2@example.com', 'João da Silva', '$2a$10$483soOKeBy0pNyzmi0aE/uPQTUzS10lNG6lwKH.apHDAmqiBAYA8a', 'DELIVERYPERSON', '11999998888');

INSERT INTO public.delivery_person (id, cpf, user_id) VALUES ('bec22f7a-0086-4282-9e86-9408bfa57e7c', '123.456.789-09', '097b4df6-8049-404b-b94e-4bd1d438374e');

INSERT INTO public.condominium (id, telephone, address, name) VALUES ('45280fc2-5f96-40aa-8d3d-691fd96e838a', '1234567890', 'Rua xxx', 'Condominio 1');


INSERT INTO public.block (condominium_id, id, name) VALUES ('45280fc2-5f96-40aa-8d3d-691fd96e838a', '57b896c1-6d1f-4c7b-80e4-15820270251c', 'Bloco A' );

INSERT INTO public.apartment (id, apartment_number, block_id, user_id) VALUES('82c527c0-b510-4bfb-89d8-00c71874f656', '101', '57b896c1-6d1f-4c7b-80e4-15820270251c', 'e1b1d8a7-cdbf-4d00-853e-76c54d173c89');

INSERT INTO public.cabinet (id, location, name, status, condominium_id) VALUES ('d373d742-860e-4382-ae09-e1bbe8e579e2', 'Terreo do bloco b', 'B001', true, '45280fc2-5f96-40aa-8d3d-691fd96e838a');

INSERT INTO public.compartment (id, is_occupied, name, size, cabinet_id) VALUES ('559321eb-8d97-4783-a03e-21ef3dc9a6f0', true, 'B001', 'SMALL', 'd373d742-860e-4382-ae09-e1bbe8e579e2');

INSERT INTO public.delivery_package (delivery_datetime, max_pickup_datetime, pickup_datetime, apartment_id, compartment_id, delivery_person_id, id, picked_up_by, status) VALUES ('2025-05-20 20:14:52.532889', '2025-05-21 20:14:52.532889', NULL, '82c527c0-b510-4bfb-89d8-00c71874f656', '559321eb-8d97-4783-a03e-21ef3dc9a6f0', 'bec22f7a-0086-4282-9e86-9408bfa57e7c', 'e01be021-b2f4-4b51-8940-3e05d04f36e3', NULL, 'PENDING_PICKUP');

INSERT INTO public.notification (id, status, type, sent_datetime, delivery_package_id) VALUES ('bb5d13bb-1fb9-4795-98bc-459fc40983e2', 'SENT', 'PENDING_PICKUP', '2025-05-24 16:01:46.102515', 'e01be021-b2f4-4b51-8940-3e05d04f36e3');








