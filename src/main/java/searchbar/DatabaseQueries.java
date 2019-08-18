/*
# query1;
SELECT * FROM dynamic_data where value like "%malisa%"; # prvo trazim podatak u dynamic data da matchuje searchbaru onda dobijem jedan ili vise row_id, column_id parova
# column id me ne zanima jer mi ne govori nista, row id me zanima jer upitom na istu tabelo po row_id polju dobijem ceo red za tabelu u kojoj se nalazi podudaranje iz search bara;
# generalno me ovo ne zanima osim sto dobijem broj rezultata

# dalje mi je za prikazivanje neophodan ceo red podataka za to su mi neophodna 2 querija prvi da mi prikaze brojeve redova koji imaju match a drugi koji ce za svaki od tih brojeva da mi da podatke
# ovaj query mi daje sve brojeve redova i onda za svaki rezultat iz njega moram da pozovem statement koji mi daje ceo red
# takodje bitno ovaj query mi daje broj puta koliko moram da uzmem row id da nadjem table_definition_id
SELECT distinct row_id FROM dynamic_data where row_id in (SELECT distinct row_id FROM dynamic_data where value like "%malisa%")
# ovaj query mi daje sve brojeve redova i onda za svaki rezultat iz njega moram da pozovem statement koji mi daje ceo red
select value from dynamic_data where row_id in (
SELECT distinct row_id FROM dynamic_data where row_id in (
SELECT distinct row_id FROM dynamic_data where value like "%malisa%"))

select * from dynamic_data where row_id in (
SELECT distinct row_id FROM dynamic_data where row_id in (
SELECT distinct row_id FROM dynamic_data where value like "%malisa%"))
# jedan od ova dva querija mi daje ceo red podataka u formatu u kom mi trebaju
# prvi mi daje samo vrednosti i njega mogu da pozovem ako podselect menjam kao promenjivu
# uglavnom sa ovime sam zavrsio sto se samih podataka tice. sad treba da nadjem zaglavlja
# za pretragu zaglavlja mogu da koristim column_id da pronadjem table_definition_id preko table_column table

# ovo opet mora iz dva koraka prvi korak je da pretrazim dinamic_data  po column_id i da nadjem kolone
# drugi korak je da table_column pretrazim koristeci dobijeni column_id
# oba koraka stavljam u jedan query i dobijam sva zaglavlja tabela koja sadrze podatak iz searcha:
 SELECT * FROM table_column where table_definition_id in (
SELECT distinct table_definition_id FROM table_column  where column_id in (
 SELECT distinct column_id FROM dynamic_data where value like "%malisa%")
);

# sada mi jos fali da bi sve lepo izgledalo da iznad tabele dodam naziv tabele da se ljudi ne gube i mozda jos i link ka toj tabeli da privezem za sam naziv
# da bi dobio naziv tabele i podatke potrebne za generisanje link kao npr page_id moram da pretrazim table 
# table_definition koristeci table_id koji dobijam kao table_definition_id is table_column tabele

 SELECT * FROM table_definition where table_id in 
(SELECT distinct table_definition_id FROM table_column where table_definition_id in (
SELECT distinct table_definition_id FROM table_column  where column_id in (
 SELECT distinct column_id FROM dynamic_data where value like "%malisa%")
));
# sada bi trebalo da imam: 
# sve redove podataka
# sve jedinstvene kolone i zaglavlja
# sve definicije tabela tj info za link + naslov tabele
# sve ovo mi je potrebno za pretragu regularnih tabela koje nemaju dodatne grupe za grupe moram novo a mozda i za assesmente
 */
package searchbar;

import java.util.List;
import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.bitbucket.pbosko.siuvs.model.TableDefinition;

/**
 *
 * @author deca
 */
public class DatabaseQueries {
    /* //the tables that contain searchable data are 
    //dynamic_group_data;
    //dynamic_data;
    //prvo radimo za dynamic data jer tu ima vise smisla.
     */
    List<DynamicData> dynamicData ;

}
