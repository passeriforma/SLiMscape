# SLiMscape
SLiMScape, a <a href="http://www.cytoscape.org/">Cytoscape</a> plugin, is a platform for performing short linear motif analyses of protein interaction networks by integrating motif discovery and search tools in a network visualization environment. This aims to aid in the discovery of novel short linear motifs, as well as visualisation of the distribution of known motifs.

##Inputs
The minimum input for SLiMScape is an interaction network which contains a Uniprot attribute for each node OR a run ID from the UNSW servers. At the moment, the name of the node needs to be its Uniprot ID, though this may change in later versions. 

##SLiMProb
SLiMProb searches the protein sequences of the selected nodes for occurrences of a specified regular expressions; useful for locating new instances of a motif found using SLiMFinder. 

SLiMProb has one input; a SLiM to be searched for in the nodes selected on the graph. To run SLiMProb you must provide a regular expressions in the motifs box and select one or more nodes. Alternatively, if you know the run ID of a previous search, you can instead input that to the Run ID textbox.

If the motif is found, the colour and shape of the target node will change.

Docsumentation of the options available in the options panel (as well as custom options) can be found <a href="http://docs.slimsuite.unsw.edu.au/software/slimsuite/readme/tools/slimsearch.html">here</a>.

##SLiMFinder
SLiMFinder aims to discover new motifs in the selected protein interaction network by searching for statistically overrepresented sequences withoin a set of proteins.

It has no mandatory inputs other than a selection of graph nodes or a run ID. If a motif is found in a node, the node will change colour and shape. The specific motif is presented in an output table, along with the Uniprot ID and other data. 

Docsumentation of the options available in the options panel (as well as custom options) can be found <a href="http://docs.slimsuite.unsw.edu.au/software/slimsuite/readme/tools/slimsearch.html">here</a>.

##Domains
TBA
