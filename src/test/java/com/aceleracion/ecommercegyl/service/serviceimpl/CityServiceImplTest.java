package com.aceleracion.ecommercegyl.service.serviceimpl;


import com.aceleracion.ecommercegyl.dto.mapper.CityMapper;
import com.aceleracion.ecommercegyl.dto.request.CityRequestDTO;
import com.aceleracion.ecommercegyl.dto.response.CityResponseDTO;
import com.aceleracion.ecommercegyl.exception.MyException;
import com.aceleracion.ecommercegyl.model.City;
import com.aceleracion.ecommercegyl.repository.CityRepository;
import com.aceleracion.ecommercegyl.repository.ProvinceRepository;
import com.aceleracion.ecommercegyl.repository.serviceimpl.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CityServiceImplTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @Mock
    private ProvinceRepository provinceRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cityService = new CityServiceImpl(cityRepository, cityMapper, provinceRepository);
    }

    @Test
    public void createCity_ValidRequest_ReturnsCityResponseDTO() {
        // Arrange
        CityRequestDTO cityRequestDTO = new CityRequestDTO();
        cityRequestDTO.setCityName("Sample City");
        // Set other required fields in cityRequestDTO

        City mockCity = new City();
        // Set properties of mockCity

        CityResponseDTO mockResponseDTO = new CityResponseDTO();
        // Set properties of mockResponseDTO

        when(cityMapper.reqToCity(cityRequestDTO, provinceRepository)).thenReturn(mockCity);
        when(cityRepository.findCityByCityName(mockCity.getCityName())).thenReturn(null);
        when(cityMapper.cityToResp(mockCity)).thenReturn(mockResponseDTO);

        // Act
        CityResponseDTO result = cityService.createCity(cityRequestDTO);

        // Assert
        assertNotNull(result);
        // Assert expected properties in result

        verify(cityMapper, times(1)).reqToCity(cityRequestDTO, provinceRepository);
        verify(cityRepository, times(1)).findCityByCityName(mockCity.getCityName());
        verify(cityRepository, times(1)).save(mockCity);
        verify(cityMapper, times(1)).cityToResp(mockCity);
    }

    @Test
    public void createCity_NullRequest_ThrowsException() {
        // Arrange
        CityRequestDTO cityRequestDTO = null;

        // Act and Assert
        assertThrows(MyException.class, () -> cityService.createCity(cityRequestDTO));
    }

    @Test
    public void createCity_CityExists_ReturnsExistingCity() {
        // Arrange
        CityRequestDTO cityRequestDTO = new CityRequestDTO();
        cityRequestDTO.setCityName("Sample City");
        // Set other required fields in cityRequestDTO

        City mockCity = new City();
        // Set properties of mockCity

        when(cityMapper.reqToCity(cityRequestDTO, provinceRepository)).thenReturn(mockCity);
        when(cityRepository.findCityByCityName(mockCity.getCityName())).thenReturn(mockCity);

        // Act and Assert
        assertThrows(MyException.class, () -> cityService.createCity(cityRequestDTO));

        verify(cityMapper, times(1)).reqToCity(cityRequestDTO, provinceRepository);
        verify(cityRepository, times(1)).findCityByCityName(mockCity.getCityName());
        verify(cityRepository, never()).save(mockCity);
        verify(cityMapper, never()).cityToResp(mockCity);
    }

    @Test
    public void createCity_InactiveCityExists_ThrowsException() {
        // Arrange
        CityRequestDTO cityRequestDTO = new CityRequestDTO();
        cityRequestDTO.setCityName("Sample City");
        // Set other required fields in cityRequestDTO

        City mockCity = new City();
        // Set properties of mockCity
        mockCity.setStatus(false);

        when(cityMapper.reqToCity(cityRequestDTO, provinceRepository)).thenReturn(mockCity);
        when(cityRepository.findCityByCityName(mockCity.getCityName())).thenReturn(mockCity);

        // Act and Assert
        assertThrows(MyException.class, () -> cityService.createCity(cityRequestDTO));

        verify(cityMapper, times(1)).reqToCity(cityRequestDTO, provinceRepository);
        verify(cityRepository, times(1)).findCityByCityName(mockCity.getCityName());
        verify(cityRepository, never()).save(mockCity);
        verify(cityMapper, never()).cityToResp(mockCity);
    }


    @Test
    public void testFindAllCities() {
        // Crear una lista de ciudades simulada para devolver en la respuesta del repositorio
        List<City> cities = new ArrayList<>();
        City city1 = new City();
        city1.setCityId(1L);
        city1.setCityName("City 1");
        cities.add(city1);

        City city2 = new City();
        city2.setCityId(2L);
        city2.setCityName("City 2");
        cities.add(city2);

        // Crear una lista de respuestas de DTO de ciudades simulada
        List<CityResponseDTO> expectedResponse = new ArrayList<>();
        CityResponseDTO responseDTO1 = new CityResponseDTO();
        responseDTO1.setCityId(1L);
        responseDTO1.setCityName("City 1");
        expectedResponse.add(responseDTO1);

        CityResponseDTO responseDTO2 = new CityResponseDTO();
        responseDTO2.setCityId(2L);
        responseDTO2.setCityName("City 2");
        expectedResponse.add(responseDTO2);

        // Configurar el comportamiento del repositorio simulado
        when(cityRepository.findAll()).thenReturn(cities);

        // Configurar el comportamiento del mapper simulado
        when(cityMapper.cityToRespList(cities)).thenReturn(expectedResponse);

        // Llamar al método que se va a probar
        List<CityResponseDTO> actualResponse = cityService.findAllCities();

        // Verificar el resultado
        assertEquals(expectedResponse, actualResponse);
    }



}
