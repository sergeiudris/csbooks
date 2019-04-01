# Copyright (c) Jupyter Development Team.
# Distributed under the terms of the Modified BSD License.
ARG BASE_CONTAINER=jupyter/scipy-notebook
FROM $BASE_CONTAINER

LABEL maintainer="Jupyter Project <jupyter@googlegroups.com>"

# Install Tensorflow
RUN conda install --quiet --yes \
    'tensorflow=1.10*' \
    'keras=2.2*' && \
    conda clean -tipsy && \
    fix-permissions $CONDA_DIR && \
    fix-permissions /home/$NB_USER

RUN jupyter labextension install @jupyter-widgets/jupyterlab-manager

# RUN conda install --yes -c conda-forge ipywidgets beakerx

# RUN git clone https://github.com/twosigma/beakerx


# RUN conda env create -n labx -f configuration.yml && \
#     source activate labx && \
#     conda install -y -c conda-forge jupyterlab && \
#     (; pip install -e . --verbose) && \
#     beakerx install && \
#     jupyter labextension install @jupyter-widgets/jupyterlab-manager && \
#     (cd js/lab; jupyter labextension install .)

RUN pip install ipyparallel && \
    pip install --upgrade calysto-scheme --user && \
    python -m calysto_scheme install --user

USER root

RUN  ipcluster nbextension enable

USER $NB_UID

RUN pip install ipywidgets && \
    jupyter nbextension enable --py widgetsnbextension