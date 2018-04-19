package fr.proxibanque.proxibanquesi.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fr.proxibanque.proxibanquesi.dao.ClientDao;
import fr.proxibanque.proxibanquesi.model.Client;

import static org.mockito.Mockito.*;

public class ServiceProxibanqueImplTest {

	private static final Client DUMMY_CLIENT = new Client("Dupont", "Bernard", "2 rue du Moulin", "44210", "Pornic",
			"0240000000");

	@Mock
	ClientDao dao;
	private ServiceProxibanqueImpl clientService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		clientService = new ServiceProxibanqueImpl();
		clientService.setDao(dao);
	}

	@Test
	public void obtenirClient_Retourne_Un_Client() {
		when(dao.obtenirClient(anyLong())).thenReturn(DUMMY_CLIENT);
		Client client = clientService.obtenirClient(1);
		assertTrue(client.equals(DUMMY_CLIENT));
		verify(dao).obtenirClient(1);
	}

}
