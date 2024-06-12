package com.example.producto_service.service;

import com.example.producto_service.model.Producto;
import com.example.producto_service.Repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProductos() {
        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Producto 1");
        producto1.setPrecio(10.0);

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Producto 2");
        producto2.setPrecio(20.0);

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoService.getAllProductos();
        assertEquals(2, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void testGetProductoById() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        producto.setPrecio(10.0);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto found = productoService.getProductoById(1L);
        assertNotNull(found);
        assertEquals("Producto 1", found.getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveProducto() {
        Producto producto = new Producto();
        producto.setNombre("Nuevo Producto");
        producto.setPrecio(15.0);

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto created = productoService.saveProducto(producto);
        assertNotNull(created);
        assertEquals("Nuevo Producto", created.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testDeleteProducto() {
        Long id = 1L;
        productoService.deleteProducto(id);
        verify(productoRepository, times(1)).deleteById(id);
    }
}
