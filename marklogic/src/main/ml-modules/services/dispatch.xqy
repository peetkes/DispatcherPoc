xquery version "1.0-ml";

module namespace resource = "http://marklogic.com/rest-api/resource/dispatch";

declare variable $TRACE-ID as xs:string := "lvbb-api";

declare function get(
  $context as map:map,
  $params  as map:map
  ) as document-node()*
{
  xdmp:log("GET called")
};

declare function put(
  $context as map:map,
  $params  as map:map,
  $input   as document-node()*
  ) as document-node()?
{
  xdmp:log("PUT called")
};

declare function post(
  $context as map:map,
  $params  as map:map,
  $input   as document-node()*
  ) as document-node()*
{
  xdmp:trace($TRACE-ID, "$context"),
  xdmp:trace($TRACE-ID, $context),
  xdmp:trace($TRACE-ID, "$params"),
  xdmp:trace($TRACE-ID, $params),
  xdmp:trace($TRACE-ID, "$input"),
  xdmp:trace($TRACE-ID, $input),
  let $opdracht := map:get($params,"opdracht")
  let $extra := map:get($params,"extra")
  return (
    xdmp:log("POST called")
  )
};

declare function delete(
  $context as map:map,
  $params  as map:map
  ) as document-node()?
{
  xdmp:log("DELETE called")
};
