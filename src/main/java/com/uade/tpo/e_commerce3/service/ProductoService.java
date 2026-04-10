package com.uade.tpo.e_commerce3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.e_commerce3.dto.ProductoDTO;
import com.uade.tpo.e_commerce3.exception.PrecioNegativoException;
import com.uade.tpo.e_commerce3.exception.ProductoNotFoundException;
import com.uade.tpo.e_commerce3.model.Producto;
import com.uade.tpo.e_commerce3.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {
 
    @Autowired
    private ProductoRepository productoRepository;
    
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public ProductoDTO getProductoById(Long id) {
        Producto producto = productoRepository.findById(id).orElse(null);
        if (producto == null) {
            throw new ProductoNotFoundException("Producto no encontrado con id: " + id );
        }
        ProductoDTO productoDTO = new ProductoDTO(producto.getId(), producto.getNombre(), producto.getDescripcion(), producto.getPrecio());
        return productoDTO;
    }

    public void deleteProductoById(Long id) {
        productoRepository.deleteById(id);
    }

    //TODO: aplicar validaciones con excepciones personalizadas
    public ProductoDTO saveProducto(ProductoDTO productoDTO) {
        // try {
        //     if (producto.getPrecio() < 0) {
        //         throw new PrecioNegativoException();
        //     }
            
        // } catch (Exception e) {
        //     throw e;
        // }

        if (productoDTO.getPrecio() < 0) {
            throw new PrecioNegativoException();
        }

        Producto producto = Producto.builder()
                .nombre(productoDTO.getNombre())
                .descripcion(productoDTO.getDescripcion())
                .precio(productoDTO.getPrecio())
                .build();
        
        Producto productoAdd= productoRepository.save(producto);
        ProductoDTO productoDTOAdd = new ProductoDTO(productoAdd.getId(), productoAdd.getNombre(), productoAdd.getDescripcion(), productoAdd.getPrecio());
        return productoDTOAdd;
    }


    public ProductoDTO updateProducto(Long id, ProductoDTO productoDTO) {
        // Producto existingProducto = getProductoById(id);
        // if (existingProducto != null) {
        //     existingProducto.setNombre(producto.getNombre());
        //     existingProducto.setDescripcion(producto.getDescripcion());
        //     return productoRepository.save(existingProducto);
        // }
        return null;
    }
}
