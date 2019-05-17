package studies.project.tripadvisor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AnalyzerDef(name = "customanalyzer", tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")}),
        @TokenFilterDef(factory = EdgeNGramFilterFactory.class, params = {@Parameter(name = "maxGramSize", value = "15")})

})
@AnalyzerDef(name = "customanalyzer_query", tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class), filters = {
        @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {@Parameter(name = "language", value = "English")})

})

@Setter
@Getter
@Entity
@Indexed
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NoArgsConstructor
@Table(name = "PLACE")
public class Place {

    @Id
    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLACE_ID")
    private Long id;

    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customanalyzer"))
    @ApiModelProperty(required = true)
    @Column(name = "NAME")
    private String name;

    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customanalyzer"))
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS_LINE")
    private String addressLine;

    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customanalyzer"))
    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Field(index = Index.YES, store = Store.YES, analyze = Analyze.YES, analyzer = @Analyzer(definition = "customanalyzer"))
    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "ENTRANCE_FEE")
    private Double entranceFee;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @ElementCollection()
    @Column(name = "CATEGORIES")
    private List<String> categories = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "place")
    @JsonManagedReference
    private Set<Comment> comments;

    @ApiModelProperty(hidden = true)
    @OneToOne(fetch = FetchType.LAZY)
    private User createdBy;

    public Place(String name, String description, String addressLine, String city, String postalCode, String country, Double entranceFee, String email, String phone, List<String> categories) {
        this.name = name;
        this.description = description;
        this.addressLine = addressLine;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
        this.entranceFee = entranceFee;
        this.email = email;
        this.phone = phone;
        this.categories = categories;
    }
}