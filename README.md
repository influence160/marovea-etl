# marovea-etl
ETL Tool for manual graphical transforming text file

Is a descktop application developed in Elipse RCP

the goal of this project is to apply a transformation to a long structured text where a manual processing would take alot of time and thus save time.

the application must display the text in a table then allow us to apply the / the same transformations on all the elements of a column

but abors to display text in a table must define how (colones separators or length of colones)

### Exemple

take for example a text with this structure

        1242	Dossiers ressources humaines	RH	3YUIY9	Consomateur	
        893	Dossier administratif	ADM	RIO94	Lecteur		
        8830	Dossier social	USR	JO938	Contributer	 
        983	Projets	USR	HHR89	Contributer	
        5563	Projets informatiques	USR	89RJRJ	Contributer
        ...

we want to transform it to have a text like this

        Dossiers ressources humaines - 1242 - 3YUIY9 
        Dossier administratif - 893 - RIO94
        Dossier social - 8830 - JO938
        Projets	- 983 - HHR89
        Projets informatiques - 5563 - 89RJRJ
        ...

The user begins by giving the path of the text file and the unique separator which is the character tab (\t).<br />
The application displays the text in a table containing columns of text separated by the column separator (\t).<br />
We have then the following columns C1, S1, C2  S2, C3, S3, C4, S4, C5 (all cells in the columns S1, S2, S3 and S4 contains the character tab).<br />
The user delete the columns C3, C5, S3 and S4.<br />
The user modifie the content of the columns S1 and S2 and give the value " - " to this columns.<br />
The user move the column C2 to the first position, S1 to the 2nd position, C1 to the 3rd, S2 to the 4rd.<br />
The user save the table content to a file.<br />
