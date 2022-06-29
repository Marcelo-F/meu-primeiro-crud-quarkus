package com.github.Marcelo.F;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Entity
public class ProdutoResource extends PanacheEntity {
    @GET
    public List<Produto> buscarTodosProdutos(){
        return Produto.listAll();
    }

    @POST
    @Transactional
    public void buscarTodosProdutos(CadastrarProdutoDTO cadastrarProdutoDTO){

        Produto p = new Produto();

        p.nome= cadastrarProdutoDTO.nome;
        p.valor = cadastrarProdutoDTO.valor;
        p.persist();


    }

    @PUT
    @Path("{id}")
    @Transactional
    public void buscarTodosProdutos(@PathParam("id") Long id, CadastrarProdutoDTO cadastrarProdutoDTO){

        Optional<Produto> p =  Produto.findByIdOptional(id);

        if (p.isPresent()){
            Produto produto = p.get();
            produto.nome= cadastrarProdutoDTO.nome;
            produto.valor = cadastrarProdutoDTO.valor;
            produto.persist();
        }else{
            throw new NotFoundException();
        }

    }


    @DELETE
    @Path("{id}")
    @Transactional
    public void buscarTodosProdutos(@PathParam("id") Long id){

        Optional<Produto> p =  Produto.findByIdOptional(id);

         p.ifPresentOrElse(Produto::delete, () ->{
             throw new NotFoundException();
         });

    }
}
