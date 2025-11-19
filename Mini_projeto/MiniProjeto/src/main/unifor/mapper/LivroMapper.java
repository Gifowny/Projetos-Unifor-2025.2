package mapper;


import dto.LivroDTO;
import model.Livro;
import model.StatusLivro;

public class LivroMapper implements Mapper<Livro, LivroDTO> {

    @Override
    public LivroDTO toDTO(Livro livro) {
        if (livro == null) return null;

        LivroDTO dto = new LivroDTO();
        dto.setId(livro.getId());
        dto.setIsbn(livro.getIsbn());
        dto.setTitulo(livro.getTitulo());
        dto.setAutor(livro.getAutor());
        dto.setAno(livro.getAno());
        dto.setStatus(livro.getStatus().toString());
        dto.setEditora(livro.getEditora());
        dto.setCategoria(livro.getCategoria());
        dto.setQuantidadeDisponivel(livro.getQuantidadeDisponivel());

        return dto;
    }

    @Override
    public Livro toEntity(LivroDTO dto) {
        if (dto == null) return null;

        Livro livro = new Livro();
        livro.setId(dto.getId());
        livro.setIsbn(dto.getIsbn());
        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setAno(dto.getAno());
        livro.setStatus(StatusLivro.fromString(dto.getStatus()));
        livro.setEditora(dto.getEditora());
        livro.setCategoria(dto.getCategoria());
        livro.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());

        return livro;
    }
}

