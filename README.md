#SLiMscape
SLiMScape, a <a href="http://www.cytoscape.org/">Cytoscape</a> plugin, is a platform for performing short linear motif analyses of protein interaction networks by integrating motif discovery and search tools in a network visualization environment. This aims to aid in the discovery of novel short linear motifs, as well as visualisation of the distribution of known motifs.

##Input
The minimum input for SLiMScape is an interaction network which contains a <a href="http://www.uniprot.org/">Uniprot</a> attribute for each node OR a run ID from the UNSW servers. At the moment, the name of the node needs to be its <a href="http://web.expasy.org/docs/userman.html#ID_line">Uniprot ID</a> or <a href="http://www.uniprot.org/help/accession_numbers">Accession Number</a> for the server to know what to do with it.

An example input with Uniprot IDs is:
<table>
<tr><td>name</tr></td>
<tr><td>P54253</tr></td>
<tr><td>O35147</tr></td>
<tr><td>Q61337</tr></td>
<tr><td>P04049</tr></td>
<tr><td>Q99683</tr></td>
<tr><td>Q9EPK5</tr></td>
</table>

An example input with a run ID is:
<table>
<tr><td>Run ID:</td><td>15020400003 (SLiMProb run)</td></tr>
</table>

Once a search has been completed, the nodes that contain SLiMs will change from the default blue circles to red diamonds. The rest of the nodes will remain as they were.

If you want to pre generate a job on the Slimsuite servers, you can do that from <a href="http://rest.slimsuite.unsw.edu.au/">here</a>. If you do this, you can input the generated run ID to Cytoscape, and a graph will be generated for you! It's also handy for larger jobs that may take a while to finish.

##Results
Results for SLiMProb are displayed in two panels. 

**Main Results Panel** contains:
- Motif: The name of the motif.
- Pattern: The motif definition.
- IC: Information content.
- N\_Occ: Number of specified SLiM occurrences.
- N\_Seq: Number of nodes the SLiM occurred in.
- N_Upc: Number of nodes in the unrelated protein cluster (UPC).
- E_Upc: Enrichment score for the unrelated protein cluster.
- p_Upc: Significance value of the unrelated protein cluster.
- pUnd_Upc: Probability of seeing the observed number of occurrences or less of the UPC.

**OCC Panel** contains:
- Motif: The motif found in the sequence.
- Seq: The name of the sequence.
- Start\_Pos: The start position of the sequence.
- End\_Pos: The end position of the sequence.

Results for SLiMFinder and QSLiMFinder are displayed identically, also in two panels:

**Main Results Panel** contains:
- Rank: Rank of the motifs found, from most to least common.
- Sig: Motif significance value.
- Pattern: Regex of the motif found.
- IC: Information content of the motif.
- OCC: Enrichment score for the occurence(s).
- Support: Number of sequences the motif occurs in (no distinction made between motifs with multiple occurences).
- UP: Unrelated proteins the motif occurs in.z

**OCC Panel** contains:
- Pattern: Regex of the motif found.
- Sig: Motif significance value.
- Seq: The name of the sequence.
- Start\_Pos: The start position of the sequence.


Further results can be obtained on the internet, at the URL: `http://rest.slimsuite.unsw.edu.au/retrieve&jobid={jobid produced when the run is performed}`. Alternatively, there is a button at the bottom of the results panel which will open the above link in your browser.


##SLiMProb
SLiMProb searches the protein sequences of the selected nodes for occurrences of a specified regular expressions; useful for locating new instances of a motif found using SLiMFinder (e.g. R[SFYW].S.P). Conventions as used in the program can be found <a href="http://en.wikipedia.org/wiki/Sequence_motif#Motif_Representation">here</a>.

SLiMProb has one input; a SLiM to be searched for in the nodes selected on the graph. To run SLiMProb you must provide a regular expressions in the motifs box and select one or more nodes. Alternatively, if you know the run ID of a previous search, you can instead input that to the Run ID textbox.

If the motif is found, the colour and shape of the target node will change.

###SLiMProb Options

**Masking**
- Disorder Masking is used to mask residues which have an IUPred disorder score of less than 0.3
- Conservation Masking is used to mask residues which have a relative local conservation score of less than X.

**SLiMChance**
- Probability Cutoff is the cutoff for returned motifs.

**Custom Parameters**
- Custom parameters can be used to add other command line arguments which can be found <a href="http://rest.slimsuite.unsw.edu.au/slimprob">here</a>.

##SLiMFinder
SLiMFinder aims to discover new motifs in the selected protein interaction network by searching for statistically overrepresented sequences within a set of proteins.

It has no mandatory inputs other than a selection of graph nodes or a run ID. If a motif is found in a node, the node will change colour and shape. The specific motif is presented in an output table, along with the Uniprot ID and other data. 


###SLiMFinder Options

**Masking**
- Disorder Masking is used to mask residues which have an IUPred disorder score of less than 0.3
- Conservation Masking is used to mask residues which have a relative local conservation score of less than X.
- Feature Masking is used to mask residues which occur in features such as domains or transmembrane regions.

**SLiMChance**
- Probability Cutoff is the cutoff for returned motifs.

**Miscellaneous Options**
- Walltime is the maximum runtime of a single run.
- Custom parameters is used to add other command line arguments which can be found <a href="http://rest.slimsuite.unsw.edu.au/slimfinder">here</a>.


##QSLiMFinder
QSLiMFinder is a modification of SLiMFinder, aimed at looking for SLiMs shared by a query sequence and one or more additional sequences. SLiMs are drawn from the "Query" sequence, then searched for in the other nodes in the set.

QSLiMFinder requires a "Query" input; the Uniprot ID or Accession Number for the node you'd like to get your SLiMs from. This node does not have to be present in the graph. If a motif is found in a selected node, the node will change colour and shape. The specific motif is presented in an output table, along with the Uniprot ID and other data. 

###QSLiMFinder Options

**Masking**
- Disorder Masking is used to mask residues which have an IUPred disorder score of less than 0.3
- Conservation Masking is used to mask residues which have a relative local conservation score of less than X.
- Feature Masking is used to mask residues which occur in features such as domains or transmembrane regions.

**SLiMChance**
- Probability Cutoff is the cutoff for returned motifs.

**Miscellaneous Options**
- Walltime is the maximum runtime of a single run.
- Custom parameters is used to add other command line arguments which can be found <a href="http://rest.slimsuite.unsw.edu.au/qslimfinder">here</a>.
