#!/bin/bash
#
# Filename: generate_book.sh
#
# Description:
#  A poorly named and written script used to generate a PDF or docx or, really
#  any output format `pandoc` supports from the OZP deployment documentation
#  source files.
#
# WARNINGS:
#  Use of this script may spoil your lunch.  Please scrap it and develop something
#  more robust.



DEFAULT_OUTPUT_FILE_BASENAME="ozp-legacy-rest"
DEFAULT_OUTPUT_FORMAT="pdf"
DEFAULT_OUTPUT_DIR=.


ORDERED_SOURCE_LIST="rest/LegacyAPIs/deleteUserPreference.md \
					 rest/LegacyAPIs/doesUserPreferenceExist.md \
					 rest/LegacyAPIs/findWidget.md \
					 rest/LegacyAPIs/getUserPreference.md \
					 rest/LegacyAPIs/getWidget.md"
					 
# !!! PANDOC (pandoc.org) is needed to generate the output file
pandoc_cmd=$(command -v pandoc)
if [ -z "$pandoc_cmd" ]; then
    echo $"pandoc (pandoc.org) is needed to generate the output file!  Please install (or add to your PATH) and rerun." >&2
    exit 1
fi

output_format=${DEFAULT_OUTPUT_FORMAT}
output_file_basename=${DEFAULT_OUTPUT_FILE_BASENAME}
output_dir=${DEFAULT_OUTPUT_DIR}

while [ $# -gt 0 ]
do
    arg=$(echo $1 | sed 's/^-+/-/')
    case "$arg" in
	fmt|format)
	    shift
	    output_format=$1
	    ;;
	fbase)
	    shift
	    output_file_basename=$1
	    ;;
	outdir)
	    shift
	    output_dir=$1
	    ;;
	*)
	    echo $"Unknown option: $arg.  Skipping." 1>&2
	    ;;
    esac
    shift
done

# Move to the source directory
# ASSUMPTION: script is located in src/scripts, so parent directory is source directory
script_dir=$(dirname $0)
script_dir=$(cd $script_dir && pwd)
doc_sources_dir=$(cd $script_dir/../ && pwd)

output_file="${output_dir}/${output_file_basename}.${output_format}"

if [ -e "${output_file}" ]; then
    ts=$(date +"%Y%m%d_%H%M%S%Z")
    echo "INFO: Moving old ${output_file} to ${output_file}.${ts}"
    mv ${output_file} ${output_file}.${ts}
    if [ -e "${output_file}" ]; then
	echo "ERROR: Unable to move/delete ${output_file} for document generation.  Please remove then rerun script." 1>&2
	exit 2
    fi
fi

(cd $doc_sources_dir && $pandoc_cmd --from=markdown_github --toc -V geometry:margin=1in --standalone -o "${output_file}" $ORDERED_SOURCE_LIST)

if [ -e "${output_file}" ]; then
    echo $"Generated file: ${output_file}"
else
    echo $"ERROR: Unable to generate file ${output_file}" 1>&2
fi
