
$(function() {
  var consequences = $("input[name=consequences]").val();
  var probability = $("input[name=probability]").val();
  var riskTypeIcon = $("input[name=risk_type_icon]").val();
  $("#m" + consequences + probability).html('<img src="/img/risks/' + riskTypeIcon + '" style="width: 64px; height: 64px;">');
  var color = $("#m" + consequences + probability).attr("style").replace("background-color: ", "").replace(";", "");
  var riskLevel, riskAdequacy;
  switch (color) {
    case "yellow":
      riskLevel = "УМЕРЕН";
      riskAdequacy = "ПРИХВАТЉИВ";
    break;

    case "orange":
      riskLevel = "ВИСОК";
      riskAdequacy = "НЕПРИХВАТЉИВ";
    break;

    case "red":
      riskLevel = "ВЕОМА ВИСОК";
      riskAdequacy = "НЕПРИХВАТЉИВ";
    break;

    default:
      riskLevel = "НИЗАК";
      riskAdequacy = "ПРИХВАТЉИВ";
  }
  $("#risk_level").html(riskLevel);
  $("#risk_adequacy").html(riskAdequacy);

  $("#matrix td").click(function() {
      var consequences = $("input[name=consequences]").val();
      var probability = $("input[name=probability]").val();
      var riskTypeIcon = $("input[name=risk_type_icon]").val();
      $("#m" + consequences + probability).html('');
      var targetId = $(this).attr("id");
      var newConsequences = targetId.charAt(1);
      var newProbability = targetId.charAt(2);
      $("input[name=consequences]").val(newConsequences);
      $("input[name=probability]").val(newProbability);
      $("#m" + newConsequences + newProbability).html('<img src="/img/risks/' + riskTypeIcon + '" style="width: 64px; height: 64px;">');
  });
});